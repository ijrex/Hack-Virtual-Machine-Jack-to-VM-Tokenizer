package token;

import tokenlib.*;

public abstract class Token {
  protected String value;
  protected TokenType type;

  public Token(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public String printValue() {
    return value;
  }

  public TokenType getType() {
    return type;
  }

  public String getLabel() {
    return null;
  }

  public Keyword getKeyword() {
    return null;
  }

  public Symbol getSymbol() {
    return null;
  }

  public String getFormattedType() {
    return this.type.toString().toLowerCase();
  }
}
