package tokens.token;

import tokens.*;

public class SymbolToken extends Token {

  public SymbolToken(String value) {
    super(value);
    this.type = TokenType.SYMBOL;
  }
}
