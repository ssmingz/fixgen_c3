class PlaceHold {
  @Test
  public void test19() {
    try {
      buildRule.executeTarget("test19");
      fail("Invalid value for type attribute");
    } catch (BuildException ex) {
    }
  }
}
