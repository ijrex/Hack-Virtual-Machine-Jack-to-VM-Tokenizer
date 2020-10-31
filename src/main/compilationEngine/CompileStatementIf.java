package compilationEngine;

import token.*;
import tokenlib.Keyword;
import tokenlib.Symbol;

import java.io.IOException;

import compilationEngine.util.*;

public class CompileStatementIf extends Compile {

  Compile compileExpression;
  Compile compileStatements1;
  Compile compileStatements2;

  public CompileStatementIf(int _tab) {
    super(_tab);
    wrapperLabel = "ifStatement";
  }

  public String handleToken(Token token) throws IOException {
    switch (pos) {
      case -1:
        return prefix(token);
      case 0:
        return parseToken(token, Match.keyword(token, Keyword.IF));
      case 1:
        return parseToken(token, Match.symbol(token, Symbol.PARENTHESIS_L));
      case 2:
        if (compileExpression == null)
          compileExpression = new CompileExpression(tab);
        return handleChildClass(compileExpression, token);
      case 3:
        return parseToken(token, Match.symbol(token, Symbol.PARENTHESIS_R));
      case 4:
        return parseToken(token, Match.symbol(token, Symbol.BRACE_L));
      case 5:
        if (compileStatements1 == null)
          compileStatements1 = new CompileStatements(tab);
        return handleChildClass(compileStatements1, token);
      case 6:
        return parseToken(token, Match.symbol(token, Symbol.BRACE_R));
      case 7:
        if (Match.keyword(token, Keyword.ELSE))
          return parseToken(token, true, 8);
        return postfix();
      case 8:
        return parseToken(token, Match.symbol(token, Symbol.BRACE_L));
      case 9:
        if (compileStatements2 == null)
          compileStatements2 = new CompileStatements(tab);
        return handleChildClass(compileStatements2, token);
      case 10:
        return parseToken(token, Match.symbol(token, Symbol.BRACE_R));
      case 11:
        return postfix();
      default:
        return fail();
    }
  }
}
