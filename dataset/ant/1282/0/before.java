class PlaceHold {
  public void testFilterChain() throws BuildException {
    executeTarget("testFilterChain");
    if (project.getProperty("testFilterChain").indexOf("World!") < 0) {
      fail("Filter Chain broken");
    }
  }
}
