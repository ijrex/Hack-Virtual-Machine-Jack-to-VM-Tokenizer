package compilationEngine.util;

import token.Token;

public class Parse {
  public static String token(Token token) {
    String type = token.getType().toString().toLowerCase();
    return "<" + type + "> " + token.getValue() + " </" + type + ">\n";
  }
}
