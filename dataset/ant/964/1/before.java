class PlaceHold {
  private static void assertTrue(boolean value, String msg) throws BuildException {
    if (!value) {
      throw new BuildException(msg);
    }
  }
}
