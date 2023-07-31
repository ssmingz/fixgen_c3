class PlaceHold {
  @Test
  public void test6() {
    try {
      buildRule.executeTarget("test6");
      fail("BuildException expected: Invalid value specified for longfile attribute.");
    } catch (BuildException ex) {
    }
  }
}
