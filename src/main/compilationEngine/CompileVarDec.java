package compilationEngine;

import token.*;

import java.io.IOException;

public class CompileVarDec extends Compile {

  public CompileVarDec(int _tab) {
    super(_tab);
    wrapperLabel = "varDec";

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
