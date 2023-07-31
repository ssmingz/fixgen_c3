class PlaceHold {
  @Test
  public void test7() {
    try {
      buildRule.executeTarget("test7");
      fail("userAgent may not be null or empty");
    } catch (BuildException ex) {
    }
  }
}
