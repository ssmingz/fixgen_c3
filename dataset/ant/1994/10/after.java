class PlaceHold {
  @Test
  public void test3() {
    try {
      buildRule.executeTarget("test1");
      fail("recursive call");
    } catch (BuildException ex) {
    }
  }
}
