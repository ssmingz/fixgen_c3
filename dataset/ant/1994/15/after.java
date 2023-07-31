class PlaceHold {
  @Test
  public void test2() {
    try {
      buildRule.executeTarget("test2");
      fail("attribute src invalid");
    } catch (BuildException ex) {
    }
  }
}
