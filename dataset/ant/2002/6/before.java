class PlaceHold {
  public void testMultipleAssertions() {
    expectBuildExceptionContaining(
        "test-multiple-assertions",
        "multiple assertions rejected",
        "Only one assertion declaration is allowed");
  }
}
