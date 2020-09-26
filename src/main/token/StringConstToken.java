package token;

import java.util.regex.Pattern;

public class StringConstToken extends Token {

  public StringConstToken() {
    pat = Pattern.compile("^\"[^\"]*\"");
  }

  public Type getType() {
    return Type.STRING_CONST;
  }
}
