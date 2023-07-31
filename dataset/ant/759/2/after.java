class PlaceHold {
  @Test
  public void testNoEmptySchemaLocation() throws Exception {
    try {
      buildRule.executeTarget("testNoEmptySchemaLocation");
      fail("Empty schema location");
    } catch (BuildException ex) {
      AntAssert.assertContains(ERROR_NO_LOCATION, ex.getMessage());
    }
  }
}
