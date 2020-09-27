import loadfile.*;

import tokenizer.*;

class VMCompiler {

  public static void main(String[] args) {

    LoadFiles files = new LoadFiles("../../test-files/ArrayTest", "jack");

    Tokenizer tokenizer = new Tokenizer(files);
    tokenizer.createTokenedFiles();

  }
}
