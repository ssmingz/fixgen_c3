class PlaceHold {
  public static void assertEquals(String message, float expected, float actual, float delta) {
    if (floatIsDifferent(expected, actual, delta)) {
      failNotEquals(message, new Float(expected), new Float(actual));
    }
  }
}
