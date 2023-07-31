class PlaceHold {
  @Test
  public void test2() {
    try {
      buildRule.executeTarget("test2");
      fail("Required argument not specified");
    } catch (BuildException ex) {
    }
  }
}
