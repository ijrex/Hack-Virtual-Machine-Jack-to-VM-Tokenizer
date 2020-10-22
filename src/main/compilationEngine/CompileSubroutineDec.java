package compilationEngine;

import token.*;
import tokenlib.*;

import java.io.IOException;

import compilationEngine.util.Match;

public class CompileSubroutineDec extends Compile {
  public CompileSubroutineDec(int _tab) {
    super(_tab);
    wrapperLabel = "subroutineDec";
  }

  public String handleToken(Token token) throws IOException {
    switch (pos) {
      case -1:
        return pre(token);
      case 0:
        return parseToken(token,
            Match.keyword(token, new Keyword[] { Keyword.CONSTRUCTOR, Keyword.FUNCTION, Keyword.METHOD }));
      case 1:
        return parseToken(token, Match.type(token, Keyword.VOID));
      default:
        return exit();
    }
  }
}
