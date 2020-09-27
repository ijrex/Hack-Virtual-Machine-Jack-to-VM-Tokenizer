package tokens.token;

import tokens.*;

public class StrConstToken extends Token {

  public StrConstToken(String value) {
    super(value);
    this.type = TokenType.STRING_CONST;
  }
}
