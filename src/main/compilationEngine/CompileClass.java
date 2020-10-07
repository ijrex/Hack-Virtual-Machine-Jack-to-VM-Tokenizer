package compilationEngine;

import token.*;
import tokenlib.Keyword;
import tokenlib.Symbol;

import java.io.IOException;

import compilationEngine.util.*;

public class CompileClass extends Compile {

  CompileSubroutine compileSubroutine;

  public CompileClass(int _tabs) {
    super(_tabs);
  }

  public String handleToken(Token token) throws IOException {
    switch (pos) {
      case -1:
        pos++;
        tab++;
        return "<class>\n" + handleToken(token);
      case 0:
        return parseToken(token, Match.keyword(token, Keyword.CLASS));
      case 1:
        return parseToken(token, Match.identifier(token));
      case 2:
        return parseToken(token, Match.symbol(token, Symbol.BRACE_L));
      case 3:
        if (compileSubroutine == null) {
          compileSubroutine = new CompileSubroutine(tab);
        }
        if (Match.isSubroutineDec(token) || compileSubroutine != null) {
          if (!compileSubroutine.isComplete()) {
            return compileSubroutine.handleToken(token);
          }
        }
      default:
        pos++;
        return "...\n";
    }
  }
}
