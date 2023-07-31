class PlaceHold {
  public void testMagicProperty() throws Exception {
    expectBuildExceptionContaining(
        "testMagicProperty", "magic property not working", ERROR_UNKNOWN_COMPILER);
  }
}
