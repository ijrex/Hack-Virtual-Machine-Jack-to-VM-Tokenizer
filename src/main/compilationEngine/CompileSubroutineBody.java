package compilationEngine;

import token.*;
import tokenlib.Keyword;
import tokenlib.Symbol;

import java.io.IOException;

import compilationEngine.util.*;

public class CompileSubroutineBody extends Compile {

  Compile varDec;
  Compile statements;

  public CompileSubroutineBody(int _tabs) {
    super(_tabs);
    wrapperLabel = "subroutineBody";
  }

  private String handleSubroutine(Compile dec, Token token) throws IOException {
    if (dec != null && !dec.isComplete()) {
      String str = dec.handleToken(token);
      if (dec.isComplete()) {
        return str + handleToken(token);
      }
      return str;
    }
    return null;
  }

  public String handleToken(Token token) throws IOException {
    switch (pos) {
      case -1:
        return preface(token);

      case 0:
        return parseToken(token, Match.symbol(token, Symbol.BRACE_L));

      case 1:
        // Var Declaration: Zero or more
        if (varDec == null && Match.keyword(token, Keyword.VAR))
          varDec = new CompileVarDec(tab);
        String handledVar = handleSubroutine(varDec, token);
        if (handledVar != null)
          return handledVar;

      case 2:
        // Statement: Zero or more
        if (statements == null && Match.isStatementDec(token))
          statements = new CompileStatement(tab);
        String handledStatement = handleSubroutine(statements, token);
        if (handledStatement != null)
          return handledStatement;

      default:
        return postface();
    }
  }
}
