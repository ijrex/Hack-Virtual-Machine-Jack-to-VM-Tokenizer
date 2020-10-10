package compilationEngine;

import token.*;
import tokenlib.Keyword;
import tokenlib.Symbol;

import java.io.IOException;

import compilationEngine.util.*;

public class CompileSubroutineBody extends Compile {

  Compile varDec;
  Compile statementDec;

  public CompileSubroutineBody(int _tabs) {
    super(_tabs);
    wrapperLabel = "subroutineBody";
  }

  public String handleToken(Token token) throws IOException {
    switch (pos) {
      case -1:
        return preface(token);
      case 0:
        return parseToken(token, Match.symbol(token, Symbol.BRACE_L));
      case 1:
        if (varDec == null && Match.keyword(token, Keyword.VAR))
          varDec = new CompileVarDec(tab);
        if (varDec != null && !varDec.isComplete()) {
          String str = varDec.handleToken(token);
          if (varDec.isComplete()) {
            return str + handleToken(token);
          }
          return str;
        }
      case 2:
        if (statementDec == null && Match.isStatementDec(token)) {
          statementDec = new CompileStatement(tab);
        }
        if (statementDec != null && !statementDec.isComplete()) {
          String str = statementDec.handleToken(token);
          if (statementDec.isComplete()) {
            return str + handleToken(token);
          }
          return str;
        }

        return token.getValue() + "\n";
      default:
        return postface();
    }
  }
}
