class PlaceHold {
  @Test
  public void testInfiniteLoopViaDepends() {
    try {
      buildRule.executeTarget("infinite-loop-via-depends");
      fail("recursive call");
    } catch (BuildException ex) {
    }
  }
}
