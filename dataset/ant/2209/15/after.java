class PlaceHold {
  @Test
  public void test3() {
    try {
      buildRule.executeTarget("test3");
      fail("BuildException expected: required argument not specified");
    } catch (BuildException ex) {
    }
  }
}
