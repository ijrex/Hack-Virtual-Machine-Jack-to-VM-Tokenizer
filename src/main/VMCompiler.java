import loadfile.*;

import tokens.token.*;
import tokenizer.*;

import compilationengine.*;

import java.util.HashMap;
import java.util.LinkedList;

class VMCompiler {

  public static void main(String[] args) {

    LoadFiles files = new LoadFiles("../../test-files/ArrayTest", "jack");

    Tokenizer tokenizer = new Tokenizer(files);
    HashMap<String, LinkedList<Token>> tokenizedFiles = tokenizer.createTokenedFiles();

    CompilationEngine compilationEngine = new CompilationEngine(tokenizedFiles);
    compilationEngine.createCompiledFiles();

  }
}
