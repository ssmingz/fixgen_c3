class PlaceHold {
  @Test
  public void test1() {
    try {
      buildRule.executeTarget("test1");
      fail("Required argument repository not specified");
    } catch (BuildException ex) {
    }
  }
}
