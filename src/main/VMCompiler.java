import loadfile.*;
import tokenizer.*;

class VMCompiler {

  public static void main(String[] args) {

    String dir = "../../test-files/Grammar/Statements/Do";

    LoadFiles files = new LoadFiles(dir, "jack");

    Tokenizer tokenizer = new Tokenizer(files);

  }
}
