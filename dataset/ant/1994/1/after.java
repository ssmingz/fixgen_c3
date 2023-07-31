class PlaceHold {
  @Test
  public void test6() {
    try {
      buildRule.executeTarget("test6");
      fail("target is file");
    } catch (BuildException ex) {
    }
  }
}
