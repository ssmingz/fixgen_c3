class PlaceHold {
  private static void assertTrue(boolean value, String msg) throws TaskException {
    if (!value) {
      throw new TaskException(msg);
    }
  }
}
