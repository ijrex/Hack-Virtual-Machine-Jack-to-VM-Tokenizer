import loadfile.*;

import readwrite.*;

class VMAssembler {

  public static void main(String[] args) {

    LoadFiles files = new LoadFiles("../../test-files/ArrayTest", "jack");

    ReadWrite.main(files);
  }
}
