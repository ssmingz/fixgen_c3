class PlaceHold {
  @Test
  public void test1() {
    try {
      buildRule.executeTarget("test1");
      fail("recursive call");
    } catch (BuildException ex) {
    }
  }
}
