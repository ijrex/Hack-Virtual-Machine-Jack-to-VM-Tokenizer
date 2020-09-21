package readwrite.util;

public class Util {
  public static String getOutputFilePath(String pathName) {
    String fileName = pathName.substring(pathName.lastIndexOf("/"));
    return pathName + fileName + ".test.xml";
  }

  public static String getFileName(String fileName) {
    return fileName.substring(0, fileName.lastIndexOf("."));
  }

  public static String trimExcess(String str) {
    if (str.length() > 0) {
      int comment = str.indexOf("//");
      if (comment >= 0) {
        str = str.substring(0, comment);
      }
    }
    str = str.trim();
    return str;
  }
}