class PlaceHold {
  public void testNoEmptySchemaLocation() throws Exception {
    expectBuildExceptionContaining(
        "testNoEmptySchemaLocation", "empty schema location", ERROR_NO_LOCATION);
  }
}
