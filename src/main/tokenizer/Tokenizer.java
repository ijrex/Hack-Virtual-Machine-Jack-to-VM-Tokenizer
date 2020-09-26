package tokenizer;

import loadfile.*;
import tokenizer.util.*;

import token.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Tokenizer {

  File[] sourceFiles;
  String sourceDir;

  Token[] tokens = new Token[] { new KeywordToken(), new SymbolToken(), new IntConstToken(), new StringConstToken(),
      new IdentifierToken() };

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
          String output = parseTokens(line);
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

  private String parseTokens(String line) throws IOException {
    String parsedLine = line;
    String output = "";

    while (parsedLine.length() > 0) {
      String token = matchToken(parsedLine);
      parsedLine = parsedLine.substring(token.length()).trim();
      output += token + "\n";
    }

    return output;
  }

  private String matchToken(String line) throws IOException {
    for (Token token : tokens) {
      String parsedToken = token.parse(line);
      if (parsedToken != null) {
        return parsedToken;
      }
    }

    System.out.println("Cannot parse: " + line);
    throw new IOException();
  }

}
