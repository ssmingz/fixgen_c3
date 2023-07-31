class PlaceHold {
  public void assertIndexCreated() {
    if (!new File(getOutputDir(), "html/index.html").exists()) {
      fail("No file index file found");
    }
  }
}
