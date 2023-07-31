class PlaceHold {
  public void testNoEmptySchemaNamespace() throws Exception {
    expectBuildExceptionContaining(
        "testNoEmptySchemaNamespace", "empty namespace URI", ERROR_NO_URI);
  }
}
