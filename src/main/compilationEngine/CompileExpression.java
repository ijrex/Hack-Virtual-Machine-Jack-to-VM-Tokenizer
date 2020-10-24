package compilationEngine;

import token.*;
import tokenlib.Keyword;
import tokenlib.Symbol;

import java.io.IOException;

import compilationEngine.util.*;

public class CompileExpression extends Compile {

  Compile compileTerm1;

  public CompileExpression(int _tab) {
    super(_tab);
    wrapperLabel = "expression";

    development = true;
  }

  public String handleToken(Token token) throws IOException {
    switch (pos) {
      case -1:
        return prefix(token);
      case 0:
        if (compileTerm1 == null)
          compileTerm1 = new CompileTerm(tab);
        return handleChildClass(compileTerm1, token);
      default:
        return fail();
    }
  }
}
