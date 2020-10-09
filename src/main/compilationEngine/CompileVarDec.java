package compilationEngine;

import token.*;
import tokenlib.Keyword;
import tokenlib.Symbol;

import java.io.IOException;

import compilationEngine.util.*;

public class CompileVarDec extends Compile {

  Compile parameterList;

  public CompileVarDec(int _tab) {
    super(_tab);
    wrapperLabel = "varDec";
  }

  public String handleToken(Token token) throws IOException {
    switch (pos) {
      case -1:
        return preface(token);
      case 0:
        return parseToken(token, Match.keyword(token, Keyword.VAR));
      case 1:
        return parseToken(token, Match.type(token));
      case 2:
        return parseToken(token, Match.identifier(token));
      case 3:
        if (Match.symbol(token, Symbol.COMMA))
          return parseToken(token, true, 2);
        return parseToken(token, Match.symbol(token, Symbol.SEMI_COLON));
      default:
        if (Match.keyword(token, Keyword.VAR))
          return postface(true) + preface(token);
        return postface();
    }
  }
}
