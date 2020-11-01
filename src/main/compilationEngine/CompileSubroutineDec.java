package compilationEngine;

import token.*;
import tokenlib.*;

import java.io.IOException;

import compilationEngine.util.Match;

public class CompileSubroutineDec extends Compile {

  Compile compileParameterList;
  Compile compileSubroutineBody;

  public CompileSubroutineDec(int _tab) {
    super(_tab);
    wrapperLabel = "subroutineDec";
  }

  public String handleToken(Token token) throws IOException {
    switch (pos) {
      case -1:
        return prefix(token);
      case 0:
        return parseToken(token,
            Match.keyword(token, new Keyword[] { Keyword.CONSTRUCTOR, Keyword.FUNCTION, Keyword.METHOD }));
      case 1:
        return parseToken(token, Match.type(token, Keyword.VOID));
      case 2:
        return parseToken(token, Match.identifier(token));
      case 3:
        return parseToken(token, Match.symbol(token, Symbol.PARENTHESIS_L));
      case 4:
        if (compileParameterList == null)
          compileParameterList = new CompileParameterList(tab);
        return handleChildClass(compileParameterList, token);
      case 5:
        return parseToken(token, Match.symbol(token, Symbol.PARENTHESIS_R));
      case 6:
        if (compileSubroutineBody == null)
          compileSubroutineBody = new CompileSubroutineBody(tab);
        return handleChildClass(compileSubroutineBody, token);
      case 7:
        return postfix();
      default:
        return fail();
    }
  }
}
