class PlaceHold {
  @Test
  public void test5() {
    try {
      buildRule.executeTarget("test5");
      fail("dest invalid (or no http-server on local machine");
    } catch (BuildException ex) {
    }
  }
}
