package readwrite;

import loadfile.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import readwrite.util.*;

public class ReadWrite {
  public static void main(LoadFiles files) {

    int currentFile = 0;

    File[] sourceFiles = files.getFiles();

    for (File sourceFile : sourceFiles) {
      try {
        String outputFile = Util.getOutputFilePath(files.getDirectoryPath());
        FileWriter fileWriter = new FileWriter(outputFile, false);

        Scanner fileScanner = new Scanner(sourceFile);

        while (fileScanner.hasNextLine()) {
          String line = fileScanner.nextLine();

          line = Util.trimExcess(line);

          if (line.length() > 0) {
            fileWriter.write("TODO PARSE: " + line + "\n");
          }
        }

        fileScanner.close();
        fileWriter.close();
        System.out.println(outputFile);

      } catch (IOException e) {
        System.out.println("An error occured parsing " + sourceFiles[currentFile].getName());
        e.printStackTrace();
        System.exit(1);
      }
    }
  }
}
