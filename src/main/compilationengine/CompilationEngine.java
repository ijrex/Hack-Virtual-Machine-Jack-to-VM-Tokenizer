package compilationengine;

import tokenlib.*;
import token.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import java.io.FileWriter;
import java.io.IOException;

public class CompilationEngine {

  HashMap<String, LinkedList<Token>> tokenizedFiles;

  public CompilationEngine(HashMap<String, LinkedList<Token>> sourceFiles) {

    tokenizedFiles = sourceFiles;
  }

  private String printToken(Token token) {
    String type = token.getType().toString().toLowerCase();
    return "<" + type + "> " + token.getValue() + " </" + type + ">\n";
  }

  private boolean matchGrammar(String value, String[] conditions) {
    for (String condition : conditions) {
      if (value.matches(condition)) {
        return true;
      }
    }
    return false;
  }

  private boolean matchGrammar(String value, String condition) {
    if (value.matches(condition)) {
      return true;
    }
    return false;
  }

  private boolean matchGrammar(TokenType type, TokenType condition) {
    if (type == condition) {
      return true;
    }
    return false;
  }

  private void compileClass(LinkedList<Token> tokenStream, FileWriter fileWriter) throws IOException {
    for (int i = 0; i < tokenStream.size(); i++) {

      Token token = tokenStream.getFirst();
      String value = token.getValue();
      TokenType type = token.getType();

      if (matchGrammar(value, Keywords.CLASS.getValue())) {
        fileWriter.write(printToken(token));
        tokenStream.removeFirst();
      }

      token = tokenStream.getFirst();
      value = token.getValue();
      type = token.getType();
      System.out.println(value);
      if (matchGrammar(type, TokenType.IDENTIFIER)) {
        fileWriter.write(printToken(token));
        tokenStream.removeFirst();
      }

      break;

    }
  }

  public void createCompiledFiles() {
    try {
      for (Map.Entry<String, LinkedList<Token>> tokenizedFile : tokenizedFiles.entrySet()) {
        FileWriter fileWriter = new FileWriter("../../test-files/ArrayTest/" + tokenizedFile.getKey() + ".test.xml",
            false);

        compileClass(tokenizedFile.getValue(), fileWriter);

        fileWriter.close();

      }
    } catch (IOException e) {
      e.printStackTrace();
      System.exit(1);
    }
  }
}
