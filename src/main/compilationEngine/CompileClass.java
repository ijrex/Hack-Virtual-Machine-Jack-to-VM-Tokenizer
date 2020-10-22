package compilationEngine;

import token.*;
import tokenlib.Keyword;
import tokenlib.Symbol;

import java.io.IOException;

import compilationEngine.util.*;

public class CompileClass extends Compile {

  Compile compileDec;

  public CompileClass(int _tabs) {
    super(_tabs);
    wrapperLabel = "class";
  }

  public String handleToken(Token token) throws IOException {
    switch (pos) {
      case -1:
        return preface(token);
      case 0:
        return parseToken(token, Match.keyword(token, Keyword.CLASS));
      case 1:
        return parseToken(token, Match.identifier(token));
      case 2:
        return parseToken(token, Match.symbol(token, Symbol.BRACE_L));
      default:
        return postface();
    }
  }
}
