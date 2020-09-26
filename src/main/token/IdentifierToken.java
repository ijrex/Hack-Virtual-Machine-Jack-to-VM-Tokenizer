package token;

import java.util.regex.Pattern;

public class IdentifierToken extends Token {

  public IdentifierToken() {
    pat = Pattern.compile("^[^\\d][\\w_]*");
  }

  public Type getType() {
    return Type.IDENTIFIER;
  }
}
