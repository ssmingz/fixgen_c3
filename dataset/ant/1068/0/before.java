class PlaceHold {
  public void testNestedFilesetPath() throws Exception {
    try {
      executeTarget("nestedFilesetPath");
    } catch (BuildException e) {
      fail("A path can contain nested filesets: " + e);
    }
  }
}
