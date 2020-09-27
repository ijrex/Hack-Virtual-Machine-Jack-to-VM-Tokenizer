package tokens;

import tokens.token.*;
import tokens.util.*;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

import java.util.Map;
import java.util.HashMap;

public class TokenTypeLib {

  Pattern keywordPat, symbolPat, intConstPat, stringConstPat, identifierPat;

  Map<String, Token> tokens = new HashMap<String, Token>();

  public TokenTypeLib() {
    keywordPat = Pattern.compile(Util.getKeywordReg());
    symbolPat = Pattern.compile(Util.getSymbolReg());
    intConstPat = Pattern.compile("^\\d");
    stringConstPat = Pattern.compile("^\"[^\"]*\"");
    identifierPat = Pattern.compile("^[A-Za-z][\\w]*");

    // Populate `tokens` map with predefined keyword and
    // symbol tokens. i.e. 'class', '{'

    for (Keywords keyword : Keywords.values()) {
      String value = keyword.getValue();
      tokens.put(value, new KeywordToken(value));
    }

    for (Symbols symbol : Symbols.values()) {
      String value = symbol.getValue();
      tokens.put(value, new SymbolToken(value));
    }
  }

  public Token getTokenTypeFromString(String line) {

    Matcher mat;

    // Check if Keyword token
    mat = keywordPat.matcher(line);
    if (mat.find())
      return tokens.get(mat.group(0));

    // Check if Symbol token
    mat = symbolPat.matcher(line);
    if (mat.find())
      return tokens.get(mat.group(0));

    // Check if Int Const token and create new
    mat = intConstPat.matcher(line);
    if (mat.find()) {
      String token = mat.group(0);
      return putNewToken(token, new IntConstToken(token));
    }

    // Check if Str Const token and create new
    mat = stringConstPat.matcher(line);
    if (mat.find()) {
      String token = mat.group(0);
      return putNewToken(token, new StrConstToken(token));
    }

    // Check if Identifier token and create new
    mat = identifierPat.matcher(line);
    if (mat.find()) {
      String token = mat.group(0);
      return putNewToken(token, new IdentifierToken(token));
    }

    return null;
  }

  private Token putNewToken(String val, Token newToken) {
    if (tokens.get(val) == null) {
      tokens.put(val, newToken);
    }
    return tokens.get(val);
  }
}
