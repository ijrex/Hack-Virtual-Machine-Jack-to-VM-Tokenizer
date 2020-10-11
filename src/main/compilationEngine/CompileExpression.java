package compilationEngine;

import token.*;
import tokenlib.TokenType;

import java.io.IOException;

import compilationEngine.util.Match;

public class CompileExpression extends Compile {

  Compile parameterList;

  Token[] lookAhead;

  public CompileExpression(int _tab) {
    super(_tab);
    wrapperLabel = "expression";
  }

  public String handleToken(Token token) throws IOException {
    switch (pos) {
      case -1:
        return preface(token);
      case 0:
        // Define term type

        // If identifier, distinguish using lookahead if
        // a variable, array entry or subroutine call
        // - Variable `(...`
        // - Array `[`
        // - Subroutine call `.`
        if (Match.identifier(token))
          lookAhead = new Token[2];

        if (lookAhead != null) {
          if (lookAhead[0] == null) {
            lookAhead[0] = token;
            return "(looking ahead...)" + token.getValue() + "\n";
          }
          if (lookAhead[1] == null) {
            pos++;
            lookAhead[1] = token;
            return lookAhead[0].getValue() + "\n" + token.getValue() + "\n";
          }
        }

      default:
        return postface();
    }
  }
}
