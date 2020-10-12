package compilationEngine;

import token.*;
import tokenlib.*;

import java.io.IOException;

import compilationEngine.util.Match;

public class CompileTerm extends Compile {

  Token lookAhead;

  public CompileTerm(int _tab) {
    super(_tab);
    wrapperLabel = "term";
  }

  public String handleToken(Token token) throws IOException {
    switch (pos) {
      case -1:
        return preface(token);
      case 0:
        // requires lookahead:
        // ------------------
        // varName `[` expression `]`
        // varName (abandon if not followed by `[`)
        // subRoutineCall:
        // - subRoutineName `(` expressionList `)`
        // - (className | varName) `.` subroutineName `(` expressionList `)`

        if (Match.identifier(token) && lookAhead == null) {
          lookAhead = token;
          return tabs() + "(look ahead `" + token.getValue() + "`)\n";
        }

        if (lookAhead != null) {
          Symbol symbol = token.getSymbol();

          switch (symbol) {
            case BRACKET_L:
              // Look for expression
              return parseToken(lookAhead, true, 0) + parseToken(token, true);
            case PERIOD:
            case PARENTHESIS_L:
              // Subroutine call
              // TODO: move subroutine to a new case... i.e. `10`
            default:
              return parseToken(lookAhead, true, 0) + parseToken(token, true, 1);
          }
        }

        // terms:
        // ---------------
        // integerConstant
        // stringConstant
        // keywordConstant
        // varName (parsed during lookup)

        if (Match.stringConst(token) || Match.intConst(token) || Match.keyword(token)) {
          return parseToken(token, true);
        }

        // `(` expression `)`
        if (Match.symbol(token, Symbol.PARENTHESIS_R)) {
          // TODO: Parse expression
        }

        // unaryOp term
        if (Match.symbol(token, new Symbol[] { Symbol.MINUS, Symbol.TILDE })) {
          // TODO: Look for term
        }

      default:
        return postface();
    }
  }
}
