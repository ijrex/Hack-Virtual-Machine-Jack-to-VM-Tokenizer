package compilationEngine;

import token.*;
import tokenlib.Keyword;
import tokenlib.Symbol;

import java.io.IOException;

import compilationEngine.util.*;

public class CompileSubroutineBody extends Compile {

  Compile dec;

  public CompileSubroutineBody(int _tabs) {
    super(_tabs);
    wrapperLabel = "subroutineBody";
  }

  private void setDecType(Token token) throws IOException {
    if (Match.isVarDec(token)) {
      dec = new CompileVarDec(tab);
      return;
    }
    if (Match.isStatementDec(token)) {
      dec = new CompileStatement(tab);
      return;
    }
    throw new IOException("ERROR: parsing \"" + token.getValue() + "\"");
  }

  public String handleToken(Token token) throws IOException {
    switch (pos) {
      case -1:
        return preface(token);
      case 0:
        return parseToken(token, Match.symbol(token, Symbol.BRACE_L));
      case 1:
        if (dec == null)
          setDecType(token);
        if (!dec.isComplete())
          return dec.handleToken(token);
      default:
        return postface();
    }
  }
}
