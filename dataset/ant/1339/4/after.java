class PlaceHold {
  @Test
  public void test5() {
    try {
      buildRule.executeTarget("test5");
      fail("ANTLR returned: 1");
    } catch (BuildException ex) {
    }
  }
}
