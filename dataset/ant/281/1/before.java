class PlaceHold {
  public void testNoDuplicateSchema() throws Exception {
    expectBuildExceptionContaining(
        "testNoDuplicateSchema", "duplicate schemas with different values", ERROR_DUPLICATE_SCHEMA);
  }
}
