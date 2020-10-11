package compilationEngine.util;

import token.*;
import tokenlib.Keyword;
import tokenlib.Symbol;
import tokenlib.TokenType;

public class Match {
  public static Boolean keyword(Token token, Keyword expected) {
    return token.getValue() == expected.getValue();
  }

  public static Boolean keyword(Token token, Keyword[] expected) {
    for (Keyword keyword : expected) {
      if (keyword(token, keyword)) {
        return true;
      }
    }
    return false;
  }

  public static Boolean symbol(Token token, Symbol expected) {
    return token.getValue() == expected.getValue();
  }

  public static Boolean identifier(Token token) {
    return token.getType() == TokenType.IDENTIFIER;
  }

  public static Boolean type(Token token) {
    Keyword[] expected = new Keyword[] { Keyword.BOOLEAN, Keyword.CHAR, Keyword.INT };
    if (keyword(token, expected)) {
      return true;
    }
    if (identifier(token)) {
      return true;
    }
    return false;
  }

  public static Boolean isSubroutineDec(Token token) {
    Keyword[] expected = new Keyword[] { Keyword.CONSTRUCTOR, Keyword.FUNCTION, Keyword.METHOD };

    return keyword(token, expected);
  }

  public static Boolean isClassVarDec(Token token) {
    Keyword[] expected = new Keyword[] { Keyword.STATIC, Keyword.FIELD };

    return keyword(token, expected);
  }

  public static Boolean isVarDec(Token token) {
    return keyword(token, Keyword.VAR);
  }

  public static Boolean isStatementDec(Token token) {
    Keyword[] expected = new Keyword[] { Keyword.LET, Keyword.IF, Keyword.WHILE, Keyword.DO, Keyword.RETURN };

    return keyword(token, expected);
  }
}