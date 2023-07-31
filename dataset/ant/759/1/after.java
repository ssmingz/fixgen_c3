class PlaceHold {
  @Test
  public void testNoEmptySchemaNamespace() throws Exception {
    try {
      buildRule.executeTarget("testNoEmptySchemaNamespace");
      fail("Empty namespace URI");
    } catch (BuildException ex) {
      AntAssert.assertContains(ERROR_NO_URI, ex.getMessage());
    }
  }
}
