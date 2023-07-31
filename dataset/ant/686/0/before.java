class PlaceHold {
  public void testValidation() {
    expectBuildExceptionContaining("testValidation", ERROR_MISSING_FILE, "file");
  }
}
