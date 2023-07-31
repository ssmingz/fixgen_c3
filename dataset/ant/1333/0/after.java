class PlaceHold {
  @Test
  public void test2() {
    try {
      buildRule.executeTarget("test2");
      fail("BuildException should have been thrown: required argument missing");
    } catch (BuildException ex) {
    }
  }
}
