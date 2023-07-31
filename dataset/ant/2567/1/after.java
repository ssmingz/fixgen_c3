class PlaceHold {
  @Test
  public void test3() {
    try {
      buildRule.executeTarget("test3");
      fail("required argument missing");
    } catch (BuildException ex) {
    }
  }
}
