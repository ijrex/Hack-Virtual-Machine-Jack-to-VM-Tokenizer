import loadfile.*;
import token.*;
import tokenizer.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.io.FileWriter;
import java.io.IOException;

class VMCompiler {

  public static void main(String[] args) {

    LoadFiles files = new LoadFiles("../../test-files/ArrayTest", "jack");

    Tokenizer tokenizer = new Tokenizer(files);
    HashMap<String, LinkedList<Token>> tokenizedFiles = tokenizer.createTokenedFiles();

    // Code below this line is temporary and is used to test
    // the tokenizer

    try {

      for (Map.Entry<String, LinkedList<Token>> tokenizedFile : tokenizedFiles.entrySet()) {
        FileWriter fileWriter = new FileWriter("../../test-files/ArrayTest/" + tokenizedFile.getKey() + ".test.vm",
            false);

        for (Token token : tokenizedFile.getValue()) {
          fileWriter.write(token.getValue() + "\n");
        }
        fileWriter.close();

      }
    } catch (IOException e) {
      e.printStackTrace();
      System.exit(1);
    }

  }
}
