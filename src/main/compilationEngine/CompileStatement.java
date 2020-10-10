package compilationEngine;

import token.*;
import tokenlib.Keyword;

import java.io.IOException;

import compilationEngine.util.Match;

public class CompileStatement extends Compile {

  Compile parameterList;

  public CompileStatement(int _tab) {
    super(_tab);
    wrapperLabel = "statements";
  }

  public String handleToken(Token token) throws IOException {
    switch (pos) {
      case -1:
        return preface(token);
      case 0:
        return parseToken(token, Match.keyword(token, Keyword.LET));
      case 1:
        return parseToken(token, Match.identifier(token));
      default:
        return postface();
    }
  }
}
