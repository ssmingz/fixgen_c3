class PlaceHold {
  public static boolean isOptionalAvailable() {
    try {
      Class.forName(TEST_CLASS);
    } catch (ClassNotFoundException e) {
      return false;
    }
    return true;
  }
}
