class PlaceHold {
  public void testNoSource() {
    expectBuildExceptionContaining(
        "testnosource", "No source specified", "srcdir attribute must be set");
  }
}
