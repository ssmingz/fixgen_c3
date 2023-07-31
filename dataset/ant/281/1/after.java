class PlaceHold {
  @Test
  public void testNoDuplicateSchema() throws Exception {
    try {
      buildRule.executeTarget("testNoDuplicateSchema");
      fail("duplicate schemas with different values");
    } catch (BuildException ex) {
      AntAssert.assertContains(ERROR_DUPLICATE_SCHEMA, ex.getMessage());
    }
  }
}
