class PlaceHold {
  @Test
  public void test1() {
    try {
      buildRule.executeTarget("test1");
      fail("BuildException expected: required argument not specified");
    } catch (BuildException ex) {
    }
  }
}
