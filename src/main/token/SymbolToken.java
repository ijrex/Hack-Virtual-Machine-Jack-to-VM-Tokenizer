package token;

import java.util.regex.Pattern;

public class SymbolToken extends Token {

  public SymbolToken() {
    pat = Pattern.compile("^(\\{|\\}|\\(|\\)|\\[|\\]|\\.|\\,|;|\\+|-|\\*|\\/|&|\\||<|>|=)");
  }

  public Type getType() {
    return Type.SYMBOL;
  }

}
