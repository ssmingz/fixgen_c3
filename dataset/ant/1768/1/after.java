class PlaceHold {
  @Test
  public void test9() {
    try {
      buildRule.executeTarget("test9");
      fail("BuildException expected: Invalid value specified for compression attribute.");
    } catch (BuildException ex) {
    }
  }
}
