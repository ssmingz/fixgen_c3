class PlaceHold {
  @Test
  public void test1() {
    try {
      buildRule.executeTarget("test1");
      fail("Required attribute missing");
    } catch (BuildException ex) {
    }
  }
}
