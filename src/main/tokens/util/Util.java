package tokens.util;

import tokens.*;

public class Util {
  public static String getKeywordReg() {
    String reg = "^(";
    for (Keywords keyword : Keywords.values()) {
      reg += keyword.getValue() + "|";
    }
    return reg.substring(0, reg.length() - 1) + ")";
  }

  public static String getSymbolReg() {
    String reg = "^(";
    for (Symbols symbol : Symbols.values()) {
      reg += "\\" + symbol.getValue() + "|";
    }
    return reg.substring(0, reg.length() - 1) + ")";
  }
}
