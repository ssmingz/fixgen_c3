class PlaceHold {
  @Test
  public void test2() {
    try {
      buildRule.executeTarget("test2");
      fail("Invalid output directory");
    } catch (BuildException ex) {
    }
  }
}
