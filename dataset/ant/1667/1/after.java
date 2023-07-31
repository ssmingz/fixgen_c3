class PlaceHold {
  @Test
  public void test7() {
    try {
      buildRule.executeTarget("test7");
      fail("BuildException expected: empty token not allowed");
    } catch (BuildException ex) {
    }
  }
}
