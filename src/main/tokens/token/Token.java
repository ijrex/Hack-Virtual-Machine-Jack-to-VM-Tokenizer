package tokens.token;

import tokens.*;

public abstract class Token {
  protected String value;
  protected TokenType type;

  public Token(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public TokenType getType() {
    return null;
  }

  public String getFormattedType() {
    return this.type.toString().toLowerCase();
  }
}
