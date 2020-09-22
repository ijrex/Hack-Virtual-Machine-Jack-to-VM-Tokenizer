package tokenizer;

import loadfile.*;
import tokenizer.util.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Tokenizer {

  File[] sourceFiles;
  String sourceDir;

  public Tokenizer(LoadFiles files) {
    sourceFiles = files.getFiles();
    sourceDir = files.getDirectoryPath();
  }

  public void createTokenedFiles() {
    for (File sourceFile : sourceFiles) {
      createTokenedFile(sourceFile);
    }
  }

  private void createTokenedFile(File sourceFile) {
    try {
      String outputFile = Util.getOutputFilePath(sourceDir);
      FileWriter fileWriter = new FileWriter(outputFile, false);

      Scanner fileScanner = new Scanner(sourceFile);

      Boolean multilineComment = false;

      while (fileScanner.hasNextLine()) {
        String line = fileScanner.nextLine();

        if (line.startsWith("/*"))
          multilineComment = true;

        Boolean multilineCommentEnd = false;
        if (line.endsWith("*/")) {
          multilineComment = false;
          multilineCommentEnd = true;
        }

        line = Util.trimExcess(line, multilineComment, multilineCommentEnd);

        if (line.length() > 0) {
          fileWriter.write(line + "\n");
        }
      }

      fileScanner.close();
      fileWriter.close();
      System.out.println(outputFile);

    } catch (IOException e) {
      System.out.println("An error occured parsing " + sourceFile.getName());
      e.printStackTrace();
      System.exit(1);
    }
  }

}
