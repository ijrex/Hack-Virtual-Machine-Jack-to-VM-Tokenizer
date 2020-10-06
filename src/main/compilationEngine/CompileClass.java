package compilationEngine;

import token.*;

public class CompileClass {
  int pos;

  public CompileClass() {
    pos = 0;
  }

  public String handleToken(Token token) {

    switch (pos) {
      case 0:
        return matchKeyword(token);
      case 1:
        return matchIdentifier(token);
      case 2:
        return matchSymbol(token);
      case 3:
        return matchIdentifier(token);
    }

    pos++;
    return pos + "..." + token.getValue() + "\n";
  }
}
