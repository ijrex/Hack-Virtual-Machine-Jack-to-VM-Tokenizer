package compilationEngine;

import token.*;

public class CompilationEngine {
  CompileClass compileClass;

  public CompilationEngine() {
    compileClass = new CompileClass();
  }

  public String parseToken(Token token) {
    String output = compileClass.handleToken(token);
    return output;
  }

}
