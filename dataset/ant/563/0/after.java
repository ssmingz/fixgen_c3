class PlaceHold {
  @Test
  public void test1() {
    try {
      buildRule.executeTarget("test1");
      fail("BuildException should have been thrown:  required argument missing");
    } catch (BuildException ex) {
    }
  }
}
