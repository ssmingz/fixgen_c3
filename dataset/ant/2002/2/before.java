class PlaceHold {
  public void testRunFailWithFailOnError() {
    expectBuildExceptionContaining(
        "testRunFailWithFailOnError", "non zero return code", "Java returned:");
  }
}
