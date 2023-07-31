class PlaceHold {
  @Test
  public void test8() {
    try {
      buildRule.executeTarget("test8");
      fail("Invalid super grammar file");
    } catch (BuildException ex) {
    }
  }
}
