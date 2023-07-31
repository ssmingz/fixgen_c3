class PlaceHold {
  @Test
  public void test3() {
    try {
      buildRule.executeTarget("test3");
      fail("You must not specify nested elements when using refid");
    } catch (BuildException ex) {
    }
  }
}
