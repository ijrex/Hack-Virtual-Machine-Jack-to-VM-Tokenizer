package compilationEngine;

import token.*;
import tokenlib.Keyword;
import tokenlib.Symbol;

import java.io.IOException;

import compilationEngine.util.*;

public class CompileSubroutineDec extends Compile {

  public CompileSubroutineDec(int _tab) {
    super(_tab);
    wrapperLabel = "subroutineDec";
  }

  public String handleToken(Token token) throws IOException {

    switch (pos) {
      case -1:
        return preface(token);
      case 0:
        return parseToken(token,
            Match.keyword(token, new Keyword[] { Keyword.CONSTRUCTOR, Keyword.FUNCTION, Keyword.METHOD }));
      case 1:
        return parseToken(token,
            Match.keyword(token, new Keyword[] { Keyword.VOID, Keyword.BOOLEAN, Keyword.CHAR, Keyword.INT }));
      case 2:
        return parseToken(token, Match.identifier(token));
      case 3:
        return parseToken(token, Match.symbol(token, Symbol.PARENTHESIS_L));
      default:
        return postface();
    }
  }
}
