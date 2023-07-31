class PlaceHold {
  @Test
  public void testFilterChain() throws BuildException {
    buildRule.executeTarget("testFilterChain");
    if (buildRule.getProject().getProperty("testFilterChain").indexOf("World!") < 0) {
      fail("Filter Chain broken");
    }
  }
}
