class PlaceHold {
  private static String normalizePattern(String p) {
    String pattern = p.replace('/', File.separatorChar).replace('\\', File.separatorChar);
    if (pattern.endsWith(File.separator)) {
      pattern += SelectorUtils.DEEP_TREE_MATCH;
    }
    return pattern;
  }
}
