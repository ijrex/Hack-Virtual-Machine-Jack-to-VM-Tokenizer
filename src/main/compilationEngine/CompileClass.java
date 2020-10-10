package compilationEngine;

import token.*;
import tokenlib.Keyword;
import tokenlib.Symbol;

import java.io.IOException;

import compilationEngine.util.*;

public class CompileClass extends Compile {

  Compile compileDec;

  public CompileClass(int _tabs) {
    super(_tabs);
    wrapperLabel = "class";
  }

  private void setDecType(Token token) throws IOException {
    if (Match.isSubroutineDec(token)) {
      compileDec = new CompileSubroutineDec(tab);
      return;
    }
    if (Match.isClassVarDec(token)) {
      compileDec = new CompileClassVarDec(tab);
      return;
    }
    throw new IOException("ERROR: parsing \"" + token.getValue() + "\"");
  }

  public String handleToken(Token token) throws IOException {
    switch (pos) {
      case -1:
        return preface(token);
      case 0:
        return parseToken(token, Match.keyword(token, Keyword.CLASS));
      case 1:
        return parseToken(token, Match.identifier(token));
      case 2:
        return parseToken(token, Match.symbol(token, Symbol.BRACE_L));
      case 3:
        // TODO: Handle multiple classVar and subroutine
        if (compileDec == null)
          setDecType(token);
        if (!compileDec.isComplete())
          return compileDec.handleToken(token);
      default:
        return postface();
    }
  }
}
