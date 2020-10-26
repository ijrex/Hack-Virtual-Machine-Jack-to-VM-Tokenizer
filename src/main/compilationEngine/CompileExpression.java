package compilationEngine;

import token.*;
import tokenlib.Keyword;
import tokenlib.Symbol;

import java.io.IOException;

import compilationEngine.util.*;

public class CompileExpression extends Compile {

  Compile compileTerm1;
  Compile compileTerm2;

  public CompileExpression(int _tab) {
    super(_tab);
    wrapperLabel = "expression";

    development = true;
  }

  public String handleToken(Token token) throws IOException {
    switch (pos) {
      case -2:
        return postfix();
      case -1:
        return prefix(token);
      case 0:
        if (compileTerm1 == null)
          compileTerm1 = new CompileTerm(tab);
        return handleChildClass(compileTerm1, token);
      case 1:
        if (Match.op(token)) {
          compileTerm2 = new CompileTerm(tab);
          return parseToken(token, true);
        }
        return postfix();
      case 2:
        return handleChildClass(compileTerm2, token);
      default:
        return fail();
    }
  }
}
