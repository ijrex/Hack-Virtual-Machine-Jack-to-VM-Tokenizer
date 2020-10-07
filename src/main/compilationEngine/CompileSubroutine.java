package compilationEngine;

import token.*;
import tokenlib.Keyword;
import tokenlib.Symbol;

import java.io.IOException;

import compilationEngine.util.*;

public class CompileSubroutine {
  int pos;
  int tab;
  boolean finished;

  public CompileSubroutine(int _tab) {
    pos = -1;
    tab = _tab;
    finished = false;
  }

  private String tabs() {
    return "\t".repeat(tab);
  }

  private String tabs(int modifier) {
    return "\t".repeat(tab + modifier);
  }

  private String parseToken(Token token, Boolean passing) throws IOException {
    if (passing) {
      pos++;

      return tabs() + Parse.token(token);
    }

    throw new IOException("ERROR: parsing \"" + token.getValue() + "\"");
  }

  public Boolean isComplete() {
    return finished;
  }

  public String handleToken(Token token) throws IOException {

    String name;

    switch (pos) {
      case -1:
        pos++;
        tab++;
        return tabs(-1) + "<subroutineDec>\n" + handleToken(token);
      case 0:
        return parseToken(token,
            Match.keyword(token, new Keyword[] { Keyword.CONSTRUCTOR, Keyword.FUNCTION, Keyword.METHOD }));
      case 1:
        return parseToken(token,
            Match.keyword(token, new Keyword[] { Keyword.VOID, Keyword.BOOLEAN, Keyword.CHAR, Keyword.INT }));
      case 2:
        name = token.getValue();
        return parseToken(token, Match.identifier(token));
      case 3:
        return parseToken(token, Match.symbol(token, Symbol.PARENTHESIS_L));
      default:
        finished = true;
        return tabs(-1) + "<subroutineDec>\n";
    }
  }
}
