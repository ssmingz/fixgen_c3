class PlaceHold {
  public void testNestedFilelistPath() throws Exception {
    try {
      executeTarget("nestedFilelistPath");
    } catch (BuildException e) {
      fail("A path can contain nested filelists: " + e);
    }
  }
}
