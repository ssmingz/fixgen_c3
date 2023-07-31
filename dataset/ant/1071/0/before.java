class PlaceHold {
  public void testNoDoubleSchemaLocation() throws Exception {
    expectBuildExceptionContaining(
        "testNoDoubleSchemaLocation", "two locations for schemas", ERROR_TWO_LOCATIONS);
  }
}
