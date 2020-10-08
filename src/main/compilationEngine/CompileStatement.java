package compilationEngine;

import token.*;

import java.io.IOException;

public class CompileStatement extends Compile {

  Compile parameterList;

  public CompileStatement(int _tab) {
    super(_tab);
    wrapperLabel = "statement";
  }

  public String handleToken(Token token) throws IOException {
    switch (pos) {
      case -1:
        return preface(token);
      case 0:
        // TODO
        return "";
      default:
        return postface();
    }
  }
}
