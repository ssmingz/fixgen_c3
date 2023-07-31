class PlaceHold {
  public static void assertEquals(String message, double expected, double actual, double delta) {
    if (doubleIsDifferent(expected, actual, delta)) {
      failNotEquals(message, new Double(expected), new Double(actual));
    }
  }
}
