class PlaceHold {
  public static void fail(String message) {
    if (message == null) {
      throw new AssertionFailedError();
    }
    throw new AssertionFailedError(message);
  }
}
