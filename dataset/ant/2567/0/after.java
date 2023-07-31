class PlaceHold {
  @Test
  public void test2() {
    try {
      buildRule.executeTarget("test2");
      fail("required argument missing");
    } catch (BuildException ex) {
    }
  }
}
