package tokenizer;

import loadfile.*;
import token.*;
import tokenlib.*;
import tokenizer.util.*;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import compilationEngine.CompilationEngine;

import java.util.LinkedList;
import java.util.HashMap;

import java.io.FileWriter;

public class Tokenizer {

  File[] sourceFiles;
  String sourceDir;

  TokenTypeLib tokenTypeLib;

  CompilationEngine compilationEngine;

  HashMap<String, LinkedList<Token>> tokenizedFiles = new HashMap<String, LinkedList<Token>>();

  public Tokenizer(LoadFiles files) {
    sourceFiles = files.getFiles();
    sourceDir = files.getDirectoryPath();

    tokenTypeLib = new TokenTypeLib();

    compilationEngine = new CompilationEngine();

    createTokenedFiles();
  }

  private void createTokenedFiles() {
    for (File sourceFile : sourceFiles) {
      createTokenedFile(sourceFile);
    }
  }

  private void createTokenedFile(File sourceFile) {

    try {
      Scanner fileScanner = new Scanner(sourceFile);

      FileWriter fileWriter = new FileWriter(Util.getOutputFilePath(sourceFile.getPath()), false);

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
          parseLineToTokens(line, fileWriter);
        }
      }

      compilationEngine.reset();
      fileWriter.close();
      fileScanner.close();

    } catch (IOException e) {
      System.out.println("An error occured parsing " + sourceFile.getName());
      e.printStackTrace();
      System.exit(1);
    }
  }

  private void parseLineToTokens(String line, FileWriter fileWriter) throws IOException {
    String parsedLine = line;

    while (parsedLine.length() > 0) {
      Token token = matchNextToken(parsedLine);
      String value = token.getValue();

      fileWriter.write(compilationEngine.parseToken(token));

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
