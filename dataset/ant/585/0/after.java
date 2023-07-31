class PlaceHold {
  @Test
  public void test2() {
    try {
      buildRule.executeTarget("test2");
      fail("Build exception should have been thrown as file attribute is required");
    } catch (BuildException ex) {
      assertEquals("file attribute required", ex.getMessage());
    }
  }
}
