package tokenizer;

import loadfile.*;
import tokenizer.util.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Tokenizer {

  File[] sourceFiles;
  String sourceDir;

  Pattern keywordRegex = Pattern.compile(
      "^(class|constructor|function|method|field|static|var|int|char|boolean|void|true|false|null|this|let|do|if|else|while|return)");
  Pattern symbolRegex = Pattern.compile("^(\\{|\\}|\\(|\\)|\\[|\\]|\\.|\\,|;|\\+|-|\\*|\\/|&|\\||<|>|=)");
  Pattern constantRegex = Pattern.compile("^\\d");
  Pattern stringRegex = Pattern.compile("^\"[^\"]*\"");
  Pattern identifierRegex = Pattern.compile("^[^\\d][\\w_]*");

  Pattern[] patterns = new Pattern[] { keywordRegex, symbolRegex, constantRegex, stringRegex, identifierRegex };

  public Tokenizer(LoadFiles files) {
    sourceFiles = files.getFiles();
    sourceDir = files.getDirectoryPath();
  }

  public void createTokenedFiles() {
    for (File sourceFile : sourceFiles) {
      createTokenedFile(sourceFile);
    }
  }

  private String matchToken(String line) throws IOException {
    for (Pattern pat : this.patterns) {
      Matcher mat = pat.matcher(line);
      if (mat.find()) {
        return mat.group(0);
      }
    }
    System.out.println("Cannot parse: " + line);
    throw new IOException();
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

}
