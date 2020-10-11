package compilationEngine;

import token.*;

import java.io.IOException;

public class CompileClassVarDec extends Compile {

  public CompileClassVarDec(int _tab) {
    super(_tab);
  }

  public String handleToken(Token token) throws IOException {

    switch (pos) {
      case -1:
        pos++;
        tab++;
        return tabs(-1) + "<classVarDec>\n" + handleToken(token);
      default:
        finished = true;
        return tabs(-1) + "<classVarDec>\n";
    }
  }
}
