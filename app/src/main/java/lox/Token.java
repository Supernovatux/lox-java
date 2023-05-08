package lox;

import java.util.OptionalInt;

class Token {
  final TokenType type;
  final String lexeme;
  final Object literal;
  final int line;

  final OptionalInt offset;

  Token(TokenType type, String lexeme, Object literal, int line) {
    this(type, lexeme, literal, line, OptionalInt.empty());
  }

  Token(TokenType type, String lexeme, Object literal, int line, OptionalInt offset) {
    this.type = type;
    this.lexeme = lexeme;
    this.literal = literal;
    this.line = line;
    this.offset = offset;
  }

  public String toString() {
    return type + " " + lexeme + " " + literal;
  }
}
