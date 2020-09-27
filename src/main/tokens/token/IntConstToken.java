package tokens.token;

import tokens.*;

public class IntConstToken extends Token {

  public IntConstToken(String value) {
    super(value);
    this.type = TokenType.INT_CONST;
  }
}
