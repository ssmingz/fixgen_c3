class PlaceHold {
  public static boolean matchPatternStart(String pattern, String str, boolean isCaseSensitive) {
    if (str.startsWith(File.separator) != pattern.startsWith(File.separator)) {
      return false;
    }
    String[] patDirs = tokenizePathAsArray(pattern);
    String[] strDirs = tokenizePathAsArray(str);
    int patIdxStart = 0;
    int patIdxEnd = patDirs.length - 1;
    int strIdxStart = 0;
    int strIdxEnd = strDirs.length - 1;
    while ((patIdxStart <= patIdxEnd) && (strIdxStart <= strIdxEnd)) {
      String patDir = patDirs[patIdxStart];
      if (patDir.equals(DEEP_TREE_MATCH)) {
        break;
      }
      if (!match(patDir, strDirs[strIdxStart], isCaseSensitive)) {
        return false;
      }
      patIdxStart++;
      strIdxStart++;
    }
    if (strIdxStart > strIdxEnd) {
      return true;
    } else if (patIdxStart > patIdxEnd) {
      return false;
    } else {
      return true;
    }
  }
}
