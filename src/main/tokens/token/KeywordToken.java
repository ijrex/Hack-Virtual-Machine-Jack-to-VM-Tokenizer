package tokens.token;

import tokens.*;

public class KeywordToken extends Token {

  public KeywordToken(String value) {
    super(value);
    this.type = TokenType.KEYWORD;
  }
}
