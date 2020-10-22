package compilationEngine;

import token.*;

import java.io.IOException;

import compilationEngine.util.*;

public abstract class Compile {
  boolean development = true;
  int tab;
  int pos = -1;
  boolean finished = false;
  String wrapperLabel;

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

  protected String parseToken(Token token, Boolean pass, int nextPos) throws IOException {
    if (pass) {
      pos = nextPos;
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

  protected String pre(Token token) throws IOException {
    pos = 0;
    tab++;
    return tabs(-1) + "<" + wrapperLabel + ">\n" + handleToken(token);
  }

  protected String post() {
    finished = true;
    return tabs(-1) + "</" + wrapperLabel + (development ? " ." : "") + ">" + "\n";
  }

  protected String exit() throws IOException {
    if (development) {
      return post();
    } else {
      throw new IOException("ERROR: Failed while parsing " + this.getClass());
    }
  }

  protected String handleToken(Token token) throws IOException {
    throw new IOException();
  }

  protected String handleChildClass(Compile compiler, Token token) throws IOException {
    if (!compiler.isComplete()) {
      String str = compiler.handleToken(token);
      if (compiler.isComplete()) {
        pos++;
        return str + handleToken(token);
      }
      return str;
    }
    throw new IOException("ERROR: parsing \"" + token.getValue() + "\"");
  }
}
