class PlaceHold {
  public static boolean matchPath(String pattern, String str) {
    String[] patDirs = tokenizePathAsArray(pattern);
    return matchPath(patDirs, str, true);
  }
}
