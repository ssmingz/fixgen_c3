class PlaceHold {
  public void tearDown() {
    try {
      if (zfPrefixAddsDir != null) {
        zfPrefixAddsDir.close();
      }
    } catch (IOException e) {
    }
    try {
      super.tearDown();
    } catch (Exception exc) {
      System.err.println(exc.getMessage());
    }
  }
}
