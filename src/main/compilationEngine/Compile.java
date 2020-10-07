package compilationEngine;

import token.*;

import java.io.IOException;

import compilationEngine.util.*;

public abstract class Compile {
  int pos;
  int tab;
  boolean finished;

  public Compile(int _tab) {
    pos = -1;
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

  protected String preface(String label) {
    pos++;
    tab++;
    return "<" + label + ">";
  }

  protected String postface(String label) {
    finished = true;
    return "</" + label + ">";
  }
}
