class PlaceHold {
  @Test
  public void test2() {
    try {
      buildRule.executeTarget("test2");
      fail("You must not specify more than one attribute when using refid");
    } catch (BuildException ex) {
    }
  }
}
