package compilationEngine;

import token.*;

import java.io.IOException;

import compilationEngine.util.*;

public abstract class Compile {
  int tab;
  int pos = -1;
  boolean finished = false;
  String wrapperLabel = "undefined";

  public Compile(int _tab) {
    tab = _tab;
    finished = false;
  }

  protected String parseToken(Token token, Boolean pass) throws IOException {
    if (pass) {
      pos++;
      return tabs() + Parse.token(token);
    }

    throw new IOException("ERROR: parsing \"" + token.getValue() + "\"");
  }

  protected Boolean isComplete() {
    return finished;
  }

  protected String tabs() {
    return "\t".repeat(tab);
  }

  protected String tabs(int modifier) {
    return "\t".repeat(tab + modifier);
  }

  protected String preface(Token token) throws IOException {
    pos++;
    tab++;
    return tabs(-1) + "<" + wrapperLabel + ">\n" + handleToken(token);
  }

  protected String postface() {
    finished = true;
    return tabs(-1) + "</" + wrapperLabel + ">\n";
  }

  protected String handleToken(Token token) throws IOException {
    throw new IOException();
  }
}
