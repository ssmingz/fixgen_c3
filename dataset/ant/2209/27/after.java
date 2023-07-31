class PlaceHold {
  @Test
  public void test3() {
    try {
      buildRule.executeTarget("test3");
      fail("BuildException should have been thrown: required argument missing");
    } catch (BuildException ex) {
    }
  }
}
