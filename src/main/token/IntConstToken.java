package token;

import java.util.regex.Pattern;

public class IntConstToken extends Token {

  public IntConstToken() {
    pat = Pattern.compile("^\\d");
  }

  public Type getType() {
    return Type.INT_CONST;
  }
}
