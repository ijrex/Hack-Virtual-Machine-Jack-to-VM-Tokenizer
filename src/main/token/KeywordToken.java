package token;

import java.util.regex.Pattern;

public class KeywordToken extends Token {

  public KeywordToken() {
    pat = Pattern.compile(
        "^(class|constructor|function|method|field|static|var|int|char|boolean|void|true|false|null|this|let|do|if|else|while|return)");
  }

  public Type getType() {
    return Type.KEYWORD;
  }
}
