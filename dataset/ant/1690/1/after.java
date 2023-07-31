class PlaceHold {
  @Test
  public void test7() {
    try {
      buildRule.executeTarget("test7");
      fail("Unable to determine generated class");
    } catch (BuildException ex) {
    }
  }
}
