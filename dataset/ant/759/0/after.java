class PlaceHold {
  @Test
  public void testNoDoubleSchemaLocation() throws Exception {
    try {
      buildRule.executeTarget("testNoDoubleSchemaLocation");
      fail("Two locations for schemas");
    } catch (BuildException ex) {
      AntAssert.assertContains(ERROR_TWO_LOCATIONS, ex.getMessage());
    }
  }
}
