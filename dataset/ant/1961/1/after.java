class PlaceHold {
  @Test
  public void test4() {
    try {
      buildRule.executeTarget("test4");
      fail("src invalid");
    } catch (BuildException ex) {
    }
  }
}
