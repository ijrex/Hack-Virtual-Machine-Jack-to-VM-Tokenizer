package compilationEngine;

import token.*;
import java.io.IOException;

public class CompileExpression extends Compile {

  Compile parameterList;

  public CompileExpression(int _tab) {
    super(_tab);
    wrapperLabel = "expression";
  }

  public String handleToken(Token token) throws IOException {
    switch (pos) {
      case -1:
        return preface(token);
      default:
        return postface();
    }
  }
}
