import loadfile.*;

import readwrite.*;

class VMCompiler {

  public static void main(String[] args) {

    LoadFiles files = new LoadFiles("../../test-files/ArrayTest", "jack");

    ReadWrite.main(files);
  }
}
