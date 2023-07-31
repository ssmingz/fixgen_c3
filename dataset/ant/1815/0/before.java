class PlaceHold {
  public static boolean matchPath(String pattern, String str, boolean isCaseSensitive) {
    String[] patDirs = tokenizePathAsArray(pattern);
    return matchPath(patDirs, str, isCaseSensitive);
  }
}
