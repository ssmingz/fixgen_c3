class PlaceHold {
  public void testEmptySource() {
    expectBuildExceptionContaining(
        "testemptysource", "No source specified", "srcdir attribute must be non-empty");
  }
}
