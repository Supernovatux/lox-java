package lox;

import java.io.Console;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Lox {
  private static final Interpreter interpreter = new Interpreter();

  static boolean hadError = false;

  static boolean hadRuntimeError = false;

  private static String prompt = "lox> ";
  private static ReplIo replIo;

  public static ReplIo getReplIo() {
    return replIo;
  }

  public static void main(final String[] args) throws IOException {
    if (args.length > 1) {
      System.out.println("Usage: jlox [script]");
      System.exit(64);
    } else if (args.length == 1) {
      runFile(args[0]);
    } else {
      runPrompt();
    }
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
    if (hadError)
      System.exit(65);
    if (hadRuntimeError)
      System.exit(70);
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
    interpreter.interpret(statements);

  }

  private static void report(final int line, final String where,
      final String message) {
    System.err.println(
        "[line " + line + "] Error" + where + ": " + message);
    hadError = true;
  }
}
