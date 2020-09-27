package tokens;

public enum Symbols {
  BRACE_L("{"), BRACE_R("}"), PARENTHESIS_L("("), PARENTHESIS_R(")"), BRACKET_L("["), BRACKET_R("]"), COMMA(","),
  PERIOD("."), SEMI_COLON(";"), PLUS("+"), MINUS("-"), ASTERISK("*"), SLASH_FWD("/"), AMPERSAND("&"), PIPE("|"),
  LESS_THAN("<"), MORE_THAN(">"), EQUALS("="), UNDERSCORE("_"),;

  private final String value;

  private Symbols(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static Symbols getType(String str) {

    for (Symbols value : Symbols.values()) {
      if (value.name().equalsIgnoreCase(str))
        return value;
    }
    return null;
  }

}
