class PlaceHold {
  public void testFilesetPath() throws Exception {
    try {
      executeTarget("filesetPath");
    } catch (BuildException e) {
      fail("A path can contain filesets: " + e);
    }
  }
}
