package lox;

import static lox.TokenType.AND;
import static lox.TokenType.BANG;
import static lox.TokenType.BANG_EQUAL;
import static lox.TokenType.CLASS;
import static lox.TokenType.COMMA;
import static lox.TokenType.DOT;
import static lox.TokenType.ELSE;
import static lox.TokenType.EOF;
import static lox.TokenType.EQUAL;
import static lox.TokenType.EQUAL_EQUAL;
import static lox.TokenType.FALSE;
import static lox.TokenType.FOR;
import static lox.TokenType.FUN;
import static lox.TokenType.GREATER;
import static lox.TokenType.GREATER_EQUAL;
import static lox.TokenType.IDENTIFIER;
import static lox.TokenType.IF;
import static lox.TokenType.LEFT_BRACE;
import static lox.TokenType.LEFT_PAREN;
import static lox.TokenType.LESS;
import static lox.TokenType.LESS_EQUAL;
import static lox.TokenType.MINUS;
import static lox.TokenType.NIL;
import static lox.TokenType.NUMBER;
import static lox.TokenType.OR;
import static lox.TokenType.PLUS;
import static lox.TokenType.PRINT;
import static lox.TokenType.RETURN;
import static lox.TokenType.RIGHT_BRACE;
import static lox.TokenType.RIGHT_PAREN;
import static lox.TokenType.SEMICOLON;
import static lox.TokenType.SLASH;
import static lox.TokenType.STAR;
import static lox.TokenType.STRING;
import static lox.TokenType.SUPER;
import static lox.TokenType.THIS;
import static lox.TokenType.TRUE;
import static lox.TokenType.VAR;
import static lox.TokenType.WHILE;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.OptionalInt;

class Scanner {
  private static final Map<String, TokenType> keywords;
  static {
    keywords = new HashMap<>();
    keywords.put("and", AND);
    keywords.put("class", CLASS);
    keywords.put("else", ELSE);
    keywords.put("false", FALSE);
    keywords.put("for", FOR);
    keywords.put("fun", FUN);
    keywords.put("if", IF);
    keywords.put("nil", NIL);
    keywords.put("or", OR);
    keywords.put("print", PRINT);
    keywords.put("return", RETURN);
    keywords.put("super", SUPER);
    keywords.put("this", THIS);
    keywords.put("true", TRUE);
    keywords.put("var", VAR);
    keywords.put("while", WHILE);
  }

  public static Map<String, TokenType> getKeywords() {
    return keywords;
  }

  private final String source;
  private final List<Token> tokens = new ArrayList<>();
  private int start = 0;

  private int current = 0;

  private int line = 1;

  Scanner(String source) {
    this.source = source;
  }

  List<Token> scanTokens() {
    while (!isAtEnd()) {
      // We are at the beginning of the next lexeme.
      start = current;
      scanToken();
    }

    tokens.add(new Token(EOF, "", null, line));
    return tokens;
  }

  private boolean isAtEnd() {
    return current >= source.length();
  }

  private void scanToken() {
    char c = advance();
    switch (c) {
      case '(':
        addToken(LEFT_PAREN);
        break;
      case ')':
        addToken(RIGHT_PAREN);
        break;
      case '{':
        addToken(LEFT_BRACE);
        break;
      case '}':
        addToken(RIGHT_BRACE);
        break;
      case ',':
        addToken(COMMA);
        break;
      case '.':
        addToken(DOT);
        break;
      case '-':
        addToken(MINUS);
        break;
      case '+':
        addToken(PLUS);
        break;
      case ';':
        addToken(SEMICOLON);
        break;
      case '*':
        addToken(STAR);
        break;
      case '!':
        addToken(match('=') ? BANG_EQUAL : BANG);
        break;
      case '=':
        addToken(match('=') ? EQUAL_EQUAL : EQUAL);
        break;
      case '<':
        addToken(match('=') ? LESS_EQUAL : LESS);
        break;
      case '>':
        addToken(match('=') ? GREATER_EQUAL : GREATER);
        break;
      case '/':
        if (match('/')) {
          // A comment goes until the end of the line.
          while (peek() != '\n' && !isAtEnd())
            advance();
        } else {
          addToken(SLASH);
        }
        break;
      case ' ':
      case '\r':
      case '\t':
        // Ignore whitespace.
        break;
      case '"':
        string();
        break;
      case '\n':
        line++;
        break;
      default:
        if (isDigit(c)) {
          number(c);
        } else if (isAlpha(c)) {
          identifier();
        } else {
          Lox.error(line, "Unexpected character.");
        }
        break;
    }
  }

  private char advance() {
    return source.charAt(current++);
  }

  private void addToken(TokenType type) {
    addToken(type, null);
  }

  private void addToken(TokenType type, Object literal) {
    String text = source.substring(start, current);
    tokens.add(new Token(type, text, literal, line, OptionalInt.of(start)));
  }

  private boolean match(char expected) {
    if (isAtEnd())
      return false;
    if (source.charAt(current) != expected)
      return false;

    current++;
    return true;
  }

  private char peek() {
    if (isAtEnd())
      return '\0';
    return source.charAt(current);
  }

  private char peekNext() {
    if (current + 1 >= source.length())
      return '\0';
    return source.charAt(current + 1);
  }

  private void string() {
    while (peek() != '"' && !isAtEnd()) {
      // handle escape sequence
      if (peek() == '\n')
        line++;
      advance();
    }

    if (isAtEnd()) {
      Lox.error(line, "Unterminated string.");
      return;
    }

    // The closing.
    advance();

    // Trim the surrounding quotes.
    String value = source.substring(start + 1, current - 1);
    addToken(STRING, value);
  }

  private boolean isDigit(char c) {
    return c >= '0' && c <= '9';
  }

  private boolean isHexDigit(char c) {
    return ((c >= '0' && c <= '9') || (c >= 'a' && c <= 'f') || (c >= 'A' && c <= 'F'));
  }

  private boolean isBinaryDigit(char c) {
    return c == '0' || c == '1';
  }

  private boolean isOctalDigit(char c) {
    return c >= '0' && c <= '7';
  }

  private void number(char prefix) {
    if (prefix == '0') {
      if (peek() == 'x' || peek() == 'X') {
        advance();
        while (isHexDigit(peek()))
          advance();
        if (start + 2 == current) {
          Lox.error(line, "Invalid hex");
          return;
        }
        long hexVal = Long.parseLong(source.substring(start + 2, current), 16);
        addToken(NUMBER, (double) hexVal);
        return;
      }
      if (peek() == 'o' || peek() == 'O') {
        advance();
        while (isOctalDigit(peek()))
          advance();
        if (start + 2 == current) {
          Lox.error(line, "Invalid octal");
          return;
        }
        long octVal = Long.parseLong(source.substring(start + 2, current), 8);
        addToken(NUMBER, (double) octVal);
        return;
      }
      if (peek() == 'b' || peek() == 'B') {
        advance();
        while (isBinaryDigit(peek()))
          advance();
        if (start + 2 == current) {
          Lox.error(line, "Invalid binary");
          return;
        }
        long binVal = Long.parseLong(source.substring(start + 2, current), 2);
        addToken(NUMBER, (double) binVal);
        return;
      }
    }
    while (isDigit(peek()))
      advance();

    // Look for a fractional part.
    if (peek() == '.' && isDigit(peekNext())) {
      // Consume the "."
      advance();

      while (isDigit(peek()))
        advance();
    }

    addToken(NUMBER,
        Double.valueOf(source.substring(start, current)));
  }

  private void identifier() {
    while (isAlphaNumeric(peek()))
      advance();
    String text = source.substring(start, current);
    TokenType type = keywords.get(text);
    if (type == null)
      type = IDENTIFIER;
    addToken(type);
  }

  private boolean isAlpha(char c) {
    return (c >= 'a' && c <= 'z') ||
        (c >= 'A' && c <= 'Z') ||
        c == '_';
  }

  private boolean isAlphaNumeric(char c) {
    return isAlpha(c) || isDigit(c);
  }
}
