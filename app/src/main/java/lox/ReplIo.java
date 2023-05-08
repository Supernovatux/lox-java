package lox;

import java.nio.file.Paths;

import org.jline.builtins.Completers.DirectoriesCompleter;
import org.jline.reader.Completer;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.reader.impl.DefaultHighlighter;
import org.jline.reader.impl.completer.AggregateCompleter;
import org.jline.reader.impl.completer.StringsCompleter;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.jline.utils.InfoCmp;
import org.jline.widget.AutopairWidgets;
import org.jline.widget.AutosuggestionWidgets;

class ReplIo {
  private Terminal terminal;
  private LineReader reader;
  private AutopairWidgets autopairWidgets;
  private AutosuggestionWidgets autosuggestionWidgets;
  private Completer completer;

  private String prompt = "lox> ";

  ReplIo() {
    try {
      terminal = TerminalBuilder.builder()
          .system(true)
          .build();
      // Add keywords to completer
      completer = new AggregateCompleter(new StringsCompleter("true", "false", "nil", "and", "class", "else", "for",
          "fun", "if", "or", "print", "return", "super", "this", "var", "while"),
          new DirectoriesCompleter(Paths.get("")));
      reader = LineReaderBuilder.builder()
          .terminal(terminal)
          .completer(completer)
          .highlighter(new DefaultHighlighter())
          .build();
      autopairWidgets = new AutopairWidgets(reader);
      autosuggestionWidgets = new AutosuggestionWidgets(reader);
      // Enable autosuggestions
      autosuggestionWidgets.enable();
      // Enable autopair
      autopairWidgets.enable();
    } catch (final Exception e) {
      System.err.println("Error initializing console reader");
      System.exit(1);
    }
  }

  public Completer getCompleter() {
    return completer;
  }

  public void setCompleter(final Completer completer) {
    this.completer = completer;
  }

  public String getPrompt() {
    return prompt;
  }

  public void setPrompt(final String prompt) {
    this.prompt = prompt;
  }

  public String readLine() {
    try {
      final String line = reader.readLine(getPrompt());
      if (line.equalsIgnoreCase("quit") || line.equalsIgnoreCase("exit")) {
        System.exit(0);
      }
      if (line.equalsIgnoreCase("cls")) {
        terminal.puts(InfoCmp.Capability.clear_screen);
        terminal.flush();
      }
      return line;
    } catch (final Exception e) {
      System.err.println("Error reading line");
      System.exit(1);
    }
    return null;
  }

  public void print(final String s) {
    terminal.writer().print(s);
  }

  public void println(final String s) {
    terminal.writer().println(s);
    terminal.writer().flush();
  }
}
