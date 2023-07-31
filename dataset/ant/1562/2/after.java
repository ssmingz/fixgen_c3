class PlaceHold {
  public static String toString(String[] line) {
    if ((line == null) || (line.length == 0)) {
      return "";
    }
    final StringBuilder result = new StringBuilder();
    for (int i = 0; i < line.length; i++) {
      if (i > 0) {
        result.append(' ');
      }
      result.append(quoteArgument(line[i]));
    }
    return result.toString();
  }
}
