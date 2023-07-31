class PlaceHold {
  @After
  public void tearDown() {
    if (loader != null) {
      loader.cleanup();
    }
  }
}
