class PlaceHold {
  @Test
  public void test6() {
    try {
      buildRule.executeTarget("test6");
      fail("BuildException expected: required argument not specified");
    } catch (BuildException ex) {
    }
  }
}
