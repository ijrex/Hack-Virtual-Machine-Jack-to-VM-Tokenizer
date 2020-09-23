package tokenizer;

import loadfile.*;
import tokenizer.util.*;

import java.util.Map;
import java.util.HashMap;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Tokenizer {

  File[] sourceFiles;
  String sourceDir;

  private Map<String, TokenType> tokens = new HashMap<String, TokenType>();

  private void assignTokenDescriptions() {
    tokens.put("class", TokenType.KEYWORD);
    tokens.put("{", TokenType.SYMBOL);
  }

  public Tokenizer(LoadFiles files) {
    sourceFiles = files.getFiles();
    sourceDir = files.getDirectoryPath();

    assignTokenDescriptions();
  }

  public void createTokenedFiles() {
    for (File sourceFile : sourceFiles) {
      createTokenedFile(sourceFile);
    }
  }

  private String parseTokens(String line) throws IOException {
    String parsedLine = line;
    String output = "";

    Pattern pat = Pattern.compile("^[^\\d][\\w_]+");

    while (parsedLine.length() > 0) {
      int lineLength = parsedLine.length();
      for (String token : tokens.keySet()) {
        if (parsedLine.startsWith(token)) {
          parsedLine = parsedLine.substring(token.length()).trim();
          output += token + "\n";
          break;
        }
      }
      Matcher mat = pat.matcher(parsedLine);
      if (mat.find()) {
        String identifier = mat.group(0);
        parsedLine = parsedLine.substring(identifier.length()).trim();
        output += identifier + "\n";
      }
      if (parsedLine.length() == lineLength) {
        System.out.println("Cannot parse: " + parsedLine);
        throw new IOException();
      }
    }

    return output;
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
          System.out.println(output);
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

}
