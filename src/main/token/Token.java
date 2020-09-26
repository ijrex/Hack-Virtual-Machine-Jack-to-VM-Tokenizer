package token;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public abstract class Token {
  protected Pattern pat;

  protected enum Type {
    KEYWORD, SYMBOL, IDENTIFIER, INT_CONST, STRING_CONST, NULL,
  }

  public Type getType() {
    return Type.NULL;
  }

  public String parse(String str) {
    Matcher mat = pat.matcher(str);
    if (mat.find()) {
      return mat.group(0);
    }
    return null;
  }
}
