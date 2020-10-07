package compilationEngine;

import token.*;
import tokenlib.Keyword;
import tokenlib.Symbol;

import java.io.IOException;

import compilationEngine.util.*;

public class CompileClass {
  int pos;
  int tab;

  CompileSubroutine compileSubroutine;

  public CompileClass() {
    pos = -1;
    tab = 0;
  }

  private String parseToken(Token token, Boolean passing) throws IOException {
    if (passing) {
      pos++;
      String tabs = "\t".repeat(tab);

      return tabs + Parse.token(token);
    }

    throw new IOException("ERROR: parsing \"" + token.getValue() + "\"");

  }

  public String handleToken(Token token) throws IOException {

    String name;

    switch (pos) {
      case -1:
        pos++;
        tab++;
        return "<class>\n" + handleToken(token);
      case 0:
        return parseToken(token, Match.keyword(token, Keyword.CLASS));
      case 1:
        name = token.getValue();
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
