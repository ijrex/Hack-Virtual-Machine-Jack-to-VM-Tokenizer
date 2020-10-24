package compilationEngine;

import token.*;
import tokenlib.Keyword;
import tokenlib.Symbol;

import java.io.IOException;

public class CompileStatements extends Compile {

  Compile compileVarDec;
  int varDecPos = 0;

  public CompileStatements(int _tab) {
    super(_tab);
    wrapperLabel = "statements";

    development = true;
  }

  public String handleToken(Token token) throws IOException {
    switch (pos) {
      case -1:
        return prefix(token);
      default:
        return fail();
    }
  }

}
