package token;

import tokenlib.*;

public class KeywordToken extends Token {

  public KeywordToken(String value) {
    super(value);
    this.type = TokenType.KEYWORD;
  }
}
