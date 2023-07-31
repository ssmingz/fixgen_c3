class PlaceHold {
  public void testWithRegex() throws Exception {
    executeTarget("testWithRegex");
    assertDebuglogContaining("ant.home=");
  }
}
