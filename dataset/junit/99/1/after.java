class PlaceHold {
  public static void assertEquals(String message, Object expected, Object actual) {
    if ((expected == null) && (actual == null)) {
      return;
    }
    if ((expected != null) && expected.equals(actual)) {
      return;
    }
    if ((expected instanceof String) && (actual instanceof String)) {
      String cleanMessage = (message == null) ? "" : message;
      throw new ComparisonFailure(cleanMessage, ((String) (expected)), ((String) (actual)));
    } else {
      failNotEquals(message, expected, actual);
    }
  }
}
