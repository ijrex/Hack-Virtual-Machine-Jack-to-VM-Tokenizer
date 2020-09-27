package tokenizer.util;

public class Util {
  public static String getOutputFilePath(String pathName) {
    String fileName = pathName.substring(pathName.lastIndexOf("/"));
    return pathName + fileName + ".temp.xml";
  }

  public static String trimExcess(String str, Boolean multilineComment, Boolean multilineCommentEnd) {
    if (multilineComment || multilineCommentEnd)
      str = "";

    if (str.length() > 0) {
      int lineComment = str.indexOf("//");
      if (lineComment >= 0) {
        str = str.substring(0, lineComment);
      }
    }

    str = str.trim();
    return str;
  }
}