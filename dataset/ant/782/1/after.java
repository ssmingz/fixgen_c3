class PlaceHold {
  @Test
  public void test1() {
    try {
      buildRule.executeTarget("test1");
      fail("required argument not specified");
    } catch (BuildException ex) {
    }
  }
}
