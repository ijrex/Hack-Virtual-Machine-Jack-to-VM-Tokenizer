package compilationEngine;

import token.*;
import tokenlib.Symbol;

import java.io.IOException;

import compilationEngine.util.*;

public class CompileSubroutineBody extends Compile {

  Compile compileVarDec;
  int varDecPos = 0;

  public CompileSubroutineBody(int _tab) {
    super(_tab);
    wrapperLabel = "subroutineBody";

    development = true;
  }

  public String handleToken(Token token) throws IOException {
    switch (pos) {
      case -1:
        return prefix(token);
      case 0:
        return parseToken(token, Match.symbol(token, Symbol.BRACE_L));
      case 1:
        // TODO: Handle Var Decs
      default:
        return fail();
    }
  }

}
