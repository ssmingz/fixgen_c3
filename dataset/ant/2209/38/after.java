class PlaceHold {
  @Test
  public void test1() {
    try {
      buildRule.executeTarget("test1");
      fail("BuildException should have been thrown - Insufficient information");
    } catch (BuildException ex) {
    }
  }
}
