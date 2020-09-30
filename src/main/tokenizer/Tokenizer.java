package tokenizer;

import loadfile.*;
import token.*;
import tokenlib.*;
import tokenizer.util.*;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import java.util.LinkedList;
import java.util.HashMap;

public class Tokenizer {

  File[] sourceFiles;
  String sourceDir;

  TokenTypeLib tokenTypeLib;

  HashMap<String, LinkedList<Token>> tokenizedFiles = new HashMap<String, LinkedList<Token>>();

  public Tokenizer(LoadFiles files) {
    sourceFiles = files.getFiles();
    sourceDir = files.getDirectoryPath();

    tokenTypeLib = new TokenTypeLib();
  }

  public HashMap<String, LinkedList<Token>> createTokenedFiles() {
    for (File sourceFile : sourceFiles) {
      createTokenedFile(sourceFile);
    }
    return tokenizedFiles;
  }

  private void createTokenedFile(File sourceFile) {

    try {
      Scanner fileScanner = new Scanner(sourceFile);

      LinkedList<Token> tokenizedFile = new LinkedList<Token>();

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
          parseLineToTokens(line, tokenizedFile);
        }
      }

      tokenizedFiles.put(sourceFile.getName(), tokenizedFile);

      fileScanner.close();

    } catch (IOException e) {
      System.out.println("An error occured parsing " + sourceFile.getName());
      e.printStackTrace();
      System.exit(1);
    }
  }

  private void parseLineToTokens(String line, LinkedList<Token> tokenizedFile) throws IOException {
    String parsedLine = line;

    while (parsedLine.length() > 0) {
      Token token = matchNextToken(parsedLine);
      String value = token.getValue();

      tokenizedFile.add(token);
      parsedLine = parsedLine.substring(value.length()).trim();
    }
  }

  private Token matchNextToken(String line) throws IOException {
    Token token = tokenTypeLib.getTokenTypeFromString(line);

    if (token != null)
      return token;

    System.out.println("Cannot parse: " + line);
    throw new IOException();
  }

}
