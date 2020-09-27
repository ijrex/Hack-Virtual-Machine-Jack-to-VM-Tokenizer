package tokenizer;

import loadfile.*;
import tokenizer.util.*;

import tokens.*;
import tokens.token.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Tokenizer {

  File[] sourceFiles;
  String sourceDir;

  TokenTypeLib tokenTypeLib;

  public Tokenizer(LoadFiles files) {
    sourceFiles = files.getFiles();
    sourceDir = files.getDirectoryPath();

    tokenTypeLib = new TokenTypeLib();
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
          String output = parseLineToTokens(line);
          fileWriter.write(output);
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

  private String parseLineToTokens(String line) throws IOException {
    String parsedLine = line;
    String output = "";

    while (parsedLine.length() > 0) {
      Token token = matchToken(parsedLine);
      String value = token.getValue();

      parsedLine = parsedLine.substring(value.length()).trim();
      output += "<" + token.getFormattedType() + "> " + value + "\n";
    }

    return output;
  }

  private Token matchToken(String line) throws IOException {
    Token token = tokenTypeLib.getTokenTypeFromString(line);

    if (token != null)
      return token;

    System.out.println("Cannot parse: " + line);
    throw new IOException();
  }

}
