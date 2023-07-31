class PlaceHold {
  @Test
  public void test6() {
    try {
      buildRule.executeTarget("test6");
      fail("Required argument not specified");
    } catch (BuildException ex) {
    }
  }
}
