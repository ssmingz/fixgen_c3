class PlaceHold {
  public void testMagicPropertyOverridesEmptyString() throws Exception {
    expectBuildExceptionContaining(
        "testMagicPropertyOverridesEmptyString",
        "magic property not working",
        ERROR_UNKNOWN_COMPILER);
  }
}
