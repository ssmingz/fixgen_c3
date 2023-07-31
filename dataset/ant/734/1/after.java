class PlaceHold {
  @Test
  public void test1() {
    try {
      buildRule.executeTarget("test1");
      fail("Build exception should have been thrown as property attribute is required");
    } catch (BuildException ex) {
      assertEquals("property attribute required", ex.getMessage());
    }
  }
}
