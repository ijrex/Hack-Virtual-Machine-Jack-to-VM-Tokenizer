package compilationEngine;

import token.*;
import tokenlib.Keyword;
import tokenlib.Symbol;

import java.io.IOException;

import compilationEngine.util.*;

public class CompileStatementLet extends Compile {

  Compile expression1;
  Compile expression2;

  public CompileStatementLet(int _tab) {
    super(_tab);
    wrapperLabel = "letStatement";

    development = true;
  }

  public String handleToken(Token token) throws IOException {
    switch (pos) {
      case -1:
        return prefix(token);
      case 0:
        return parseToken(token, Match.keyword(token, Keyword.LET));
      case 1:
        return parseToken(token, Match.identifier(token));
      case 2:
        if (Match.symbol(token, Symbol.BRACKET_L)) {
          // TODO: expression1
        }
        pos++;
      case 3:
        if (expression1 != null)
          return parseToken(token, Match.symbol(token, Symbol.BRACKET_R));
        pos++;
      case 4:
        return parseToken(token, Match.symbol(token, Symbol.EQUALS));
      case 5:
        if (expression2 == null)
          expression2 = new CompileExpression(tab);
        return handleChildClass(expression2, token);
      default:
        return fail();
    }
  }
}
