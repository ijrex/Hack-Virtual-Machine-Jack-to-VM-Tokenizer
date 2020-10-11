package compilationEngine;

import token.*;
import tokenlib.Keyword;

import java.io.IOException;

public class CompileStatement extends Compile {

  Compile statement;

  public CompileStatement(int _tab) {
    super(_tab);
    wrapperLabel = "statements";
  }

  public String handleToken(Token token) throws IOException {
    switch (pos) {
      case -1:
        return preface(token);
      case 0:
        if (statement == null) {
          Keyword statementType = token.getKeyword();

          switch (statementType) {
            case LET:
              statement = new CompileStatementLet(tab);
              // TODO: new let statement and proceed to 2
            case IF:
              // TODO: new if statement and proceed to 2
            case WHILE:
              // TODO: new while statement and proceed to 2
            case DO:
              // TODO: new do statement and proceed to 2
            case RETURN:
              // TODO: new return statement and proceed to 2
            default:
          }
        }
      case 1:
        if (!statement.isComplete())
          return statement.handleToken(token);
      default:
        return postface();
    }
  }
}
