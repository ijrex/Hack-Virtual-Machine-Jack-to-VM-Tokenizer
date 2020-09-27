package tokens.token;

import tokens.*;

public class IdentifierToken extends Token {

  public IdentifierToken(String value) {
    super(value);
    this.type = TokenType.IDENTIFIER;

  }
}
