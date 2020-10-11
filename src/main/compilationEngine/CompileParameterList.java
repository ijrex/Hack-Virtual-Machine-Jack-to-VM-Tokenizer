package compilationEngine;

import token.*;
import tokenlib.Symbol;

import java.io.IOException;

import compilationEngine.util.*;

public class CompileParameterList extends Compile {

  public CompileParameterList(int _tabs) {
    super(_tabs);
    wrapperLabel = "parameterList";
  }

  private boolean endOfArgs(Token token) {
    return Match.symbol(token, Symbol.PARENTHESIS_R);
  }

  public String handleToken(Token token) throws IOException {

    switch (pos) {
      case -1:
        if (endOfArgs(token))
          return preface(null) + postface();
        return preface(token);
      case 0:
        return parseToken(token, Match.type(token));
      case 1:
        return parseToken(token, Match.identifier(token));
      case 2:
        if (endOfArgs(token))
          return postface();
        return parseToken(token, Match.symbol(token, Symbol.COMMA), 0);
      default:
        return postface();
    }
  }
}
