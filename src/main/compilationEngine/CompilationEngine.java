package compilationEngine;

import java.io.IOException;

import token.*;

public class CompilationEngine {
  CompileClass compileClass;

  public CompilationEngine() {
    compileClass = new CompileClass();
  }

  public String parseToken(Token token) throws IOException {
    return compileClass.handleToken(token);
  }

}
