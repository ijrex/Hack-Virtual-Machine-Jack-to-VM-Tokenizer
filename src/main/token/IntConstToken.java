package token;

import tokenlib.*;

public class IntConstToken extends Token {

  public IntConstToken(String value) {
    super(value);
    this.type = TokenType.INT_CONST;
  }

  public String getLabel() {
    return "integerConstant";
  }
}
