class PlaceHold {
  public void testPathelementLocationPath() throws Exception {
    try {
      executeTarget("pathelementLocationPath");
    } catch (BuildException e) {
      fail("A path can contain pathelements pointing to a file: " + e);
    }
  }
}
