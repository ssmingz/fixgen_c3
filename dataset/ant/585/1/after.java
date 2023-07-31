class PlaceHold {
  @Test
  public void test3() {
    try {
      buildRule.executeTarget("test3");
      fail("Build exception should have been thrown as property attribute is required");
    } catch (BuildException ex) {
      assertEquals("property attribute required", ex.getMessage());
    }
  }
}
