package compilationEngine;

import token.*;
import tokenlib.Keyword;
import tokenlib.Symbol;

import java.io.IOException;

import compilationEngine.util.*;

public class CompileTerm extends Compile {

  Compile compileExpressionList;

  Token lookAhead;

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
        if (Match.intConst(token) || Match.stringConst(token) || Match.keywordConst(token))
          return parseToken(token, true, -2);
        if (Match.identifier(token)) {
          lookAhead = token;
          pos++;
          return "";
        }
        // TODO: Handle this
        return fail();
      case 2:
        if (lookAhead != null) {
          Symbol symbol = token.getSymbol();

          switch (symbol) {
            case BRACKET_L:
              // TODO: Look for expression
              break;
            case PERIOD:
              return parseToken(lookAhead, true) + parseToken(token, true, 100);
            case PARENTHESIS_L:
              // TODO: Look for expression list
              break;
            case PARENTHESIS_R:
              return parseToken(lookAhead, true) + postfix();
            default:
              // TODO: Look for expression
              break;
          }
        }
        // TODO: Handle this
        return fail();
      case 100:
        return parseToken(token, Match.identifier(token));
      case 101:
        return parseToken(token, Match.symbol(token, Symbol.PARENTHESIS_L));
      case 102:
        if (compileExpressionList == null)
          compileExpressionList = new CompileExpressionList(tab);
        return handleChildClass(compileExpressionList, token);
      case 103:
        return parseToken(token, Match.symbol(token, Symbol.PARENTHESIS_R));
      case 104:
        return postfix();
      default:
        return fail();
    }
  }
}
