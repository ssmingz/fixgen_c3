class PlaceHold {
  public void testFilelistPath() throws Exception {
    try {
      executeTarget("filelistPath");
    } catch (BuildException e) {
      fail("A path can contain filelists: " + e);
    }
  }
}
