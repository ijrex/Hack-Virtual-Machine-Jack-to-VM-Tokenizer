package compilationEngine;

import token.*;
import tokenlib.Keyword;

import java.io.IOException;

import compilationEngine.util.Match;

public class CompileStatements extends Compile {

  Compile compileStatement;

  public CompileStatements(int _tab) {
    super(_tab);
    wrapperLabel = "statements";

    development = true;
  }

  public String handleToken(Token token) throws IOException {
    switch (pos) {
      case -1:
        return prefix(token);
      case 0:
        if (Match.isStatementDec(token) && compileStatement == null) {
          Keyword statementType = token.getKeyword();

          switch (statementType) {
            case LET:
              compileStatement = new CompileStatementLet(tab);
              break;
            case IF:
              // TODO: Handle `if`
            case WHILE:
              // TODO: Handle `while`
            case DO:
              // TODO: Handle `do`
            case RETURN:
              // TODO: Handle `return`
            default:
              fail();
          }
        }
        if (compileStatement != null)
          return handleChildClass(compileStatement, token);
      case 1:
        if (Match.isStatementDec(token) && compileStatement != null) {
          compileStatement = null;
          pos--;
          return handleToken(token);
        }
      default:
        return postfix();
    }
  }

}
