import loadfile.*;
import tokenizer.*;

class VMCompiler {

  public static void main(String[] args) {

    LoadFiles files = new LoadFiles("../../test-files/ArrayTest", "jack");
    System.out.println(System.getProperty("user.dir"));

    Tokenizer tokenizer = new Tokenizer(files);

  }
}
