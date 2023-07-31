class PlaceHold {
  @Test
  public void test2() {
    try {
      buildRule.executeTarget("test2");
      fail("BuildException should have been thrown - Invalid destination file");
    } catch (BuildException ex) {
    }
  }
}
