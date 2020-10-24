package compilationEngine;

import token.*;
import tokenlib.Keyword;
import tokenlib.Symbol;

import java.io.IOException;

import compilationEngine.util.*;

public class CompileTerm extends Compile {

  Compile term1;
  String lookahead;

  public CompileTerm(int _tab) {
    super(_tab);
    wrapperLabel = "term";

    development = true;
  }

  public String handleToken(Token token) throws IOException {
    switch (pos) {
      case -2:
        return postfix();
      case -1:
        return prefix(token);
      case 0:
        if (Match.unaryOp(token))
          return parseToken(token, true);
        pos++;
      case 1:
        if (Match.intConst(token) || Match.stringConst(token) || Match.keywordConst(token)) {
          // TODO: Handle this
        }
        if (Match.identifier(token)) {
          // TODO: Handle lookahead
        }

      default:
        return fail();
    }
  }
}
