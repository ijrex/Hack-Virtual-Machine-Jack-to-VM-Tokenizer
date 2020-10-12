package compilationEngine;

import token.*;

import java.io.IOException;

public class CompileExpression extends Compile {

  Compile compileTerm;

  Token lookAhead;

  public CompileExpression(int _tab) {
    super(_tab);
    wrapperLabel = "expression";
  }

  public String handleToken(Token token) throws IOException {
    switch (pos) {
      case -1:
        return preface(token);
      case 0:
        if (compileTerm == null)
          compileTerm = new CompileTerm(tab);
        if (!compileTerm.isComplete())
          return compileTerm.handleToken(token);
      default:
        return postface();
    }
  }
}
