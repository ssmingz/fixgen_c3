class PlaceHold {
  public void tearDown() {
    if (loader != null) {
      loader.cleanup();
    }
    try {
      super.tearDown();
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
  }
}
