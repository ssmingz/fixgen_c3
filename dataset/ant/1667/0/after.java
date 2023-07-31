class PlaceHold {
  @Test
  public void test4() {
    try {
      buildRule.executeTarget("test4");
      fail("BuildException expected: empty token not allowed");
    } catch (BuildException ex) {
    }
  }
}
