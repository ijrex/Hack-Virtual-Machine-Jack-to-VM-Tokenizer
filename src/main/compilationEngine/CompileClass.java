package compilationEngine;

import token.*;
import tokenlib.Keyword;
import tokenlib.Symbol;

import java.io.IOException;

import compilationEngine.util.*;

public class CompileClass extends Compile {

  Compile compileDec;

  public CompileClass(int _tab) {
    super(_tab);
    wrapperLabel = "class";

    development = true;
  }

  public String handleToken(Token token) throws IOException {
    switch (pos) {
      case -1:
        return prefix(token);
      case 0:
        return parseToken(token, Match.keyword(token, Keyword.CLASS));
      case 1:
        return parseToken(token, Match.identifier(token));
      case 2:
        return parseToken(token, Match.symbol(token, Symbol.BRACE_L));
      case 3:
        // TODO: Parse class var dec or subroutine dec
        if (compileDec == null)
          compileDec = new CompileSubroutineDec(tab);
        return handleChildClass(compileDec, token);

      default:
        return fail();
    }
  }
}
