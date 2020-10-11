package compilationEngine;

import token.*;
import tokenlib.Keyword;
import tokenlib.Symbol;

import java.io.IOException;

import compilationEngine.util.Match;

public class CompileStatementLet extends Compile {

  Compile expression;

  public CompileStatementLet(int _tab) {
    super(_tab);
    wrapperLabel = "letStatement";
  }

  public String handleToken(Token token) throws IOException {
    switch (pos) {
      case -1:
        return preface(token);
      case 0:
        return parseToken(token, Match.keyword(token, Keyword.LET));
      case 1:
        return parseToken(token, Match.identifier(token));
      case 2:
        // TODO: Parse [] expression
        if (Match.symbol(token, Symbol.BRACKET_L))
          return "TODO";
        pos++;
      case 3:
        return parseToken(token, Match.symbol(token, Symbol.EQUALS));
      case 4:
        if (expression == null)
          expression = new CompileExpression(tab);
        if (!expression.isComplete())
          return expression.handleToken(token);
      default:
        return postface();
    }
  }
}
