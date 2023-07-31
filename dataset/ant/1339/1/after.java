class PlaceHold {
  @Test
  public void test1() {
    try {
      buildRule.executeTarget("test1");
      fail("required argument, target, missing");
    } catch (BuildException ex) {
    }
  }
}
