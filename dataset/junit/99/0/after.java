class PlaceHold {
  public static void assertEquals(String message, String expected, String actual) {
    if ((expected == null) && (actual == null)) {
      return;
    }
    if ((expected != null) && expected.equals(actual)) {
      return;
    }
    String cleanMessage = (message == null) ? "" : message;
    throw new ComparisonFailure(cleanMessage, expected, actual);
  }
}
