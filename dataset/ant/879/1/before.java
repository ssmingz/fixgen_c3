class PlaceHold {
  private static String normalizePattern(String p) {
    String pattern = p.replace('/', File.separatorChar).replace('\\', File.separatorChar);
    if (pattern.endsWith(File.separator)) {
      pattern += "**";
    }
    return pattern;
  }
}
