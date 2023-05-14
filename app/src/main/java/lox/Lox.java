package lox;

import java.awt.*;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Lox {
  private static Interpreter interpreter;

  private static boolean hadError;

  private static boolean hadRuntimeError;

  private static String prompt = "lox> ";
  private static ReplIo replIo;

  private static int scriptExitCode;
  private static boolean debug = false;
  private static int replExitCode;

  public static ReplIo getReplIo() {
    return replIo;
  }

  public static void main(final String[] args) throws IOException {
    interpreter = new Interpreter();
    hadError = false;
    hadRuntimeError = false;
    if (args.length > 3) {
      System.out.println("Usage: jlox [script]");
      System.exit(64);
    } else if (args.length == 2 && args[1].equals("--debug")) {
      debug = true;
      runFile(args[0]);
    } else if (args.length == 1) {
      debug = false;
      scriptExitCode = 65;
      replExitCode = 70;
      runFile(args[0]);
    } else {
      runPrompt();
    }
    System.out.flush();
  }

  // The error() method is called when the scanner or parser detects an error.
  static void error(final int line, final String message) {
    report(line, "", message);
  }

  static void error(final Token token, final String message) {
    if (token.type == TokenType.EOF) {
      report(token.line, " at end", message);
    } else {
      report(token.line, " at '" + token.lexeme + "'", message);
    }
  }

  static void runtimeError(final RuntimeError error) {
    System.err.println(error.getMessage() +
        "\n[line " + error.token.line + "]");
    hadRuntimeError = true;
  }

  private static void runFile(final String path) throws IOException {
    final byte[] bytes = Files.readAllBytes(Paths.get(path));
    run(new String(bytes, Charset.defaultCharset()));
    // Indicate an error in the exit code.
    if (debug == true && (hadError || hadRuntimeError)) {
      return;
    }
    if (hadError) {
      System.exit(scriptExitCode);
    }
    if (hadRuntimeError) {
      System.exit(replExitCode);
    }
  }

  private static void runPrompt() throws IOException {
    replIo = new ReplIo();
    replIo.setPrompt(prompt);
    while (true) {
      final String line = replIo.readLine();
      if (line == null)
        break;
      run(line);
      hadError = false;
    }
  }

  private static void run(final String source) {
    final Scanner scanner = new Scanner(source);
    final List<Token> tokens = scanner.scanTokens();
    final Parser parser = new Parser(tokens);
    List<Stmt> statements = parser.parse();

    // Stop if there was a syntax error.
    if (hadError)
      return;
    Resolver resolver = new Resolver(interpreter);
    resolver.resolve(statements);
    if (hadError)
      return;
    interpreter.interpret(statements);

  }

  private static void report(final int line, final String where,
      final String message) {
    System.err.println(
        "[line " + line + "] Error" + where + ": " + message);
    hadError = true;
  }
}
