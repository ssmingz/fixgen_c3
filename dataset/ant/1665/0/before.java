class PlaceHold {
  public void testRunFailFoeFork() {
    expectBuildExceptionContaining(
        "testRunFailFoeFork", "java failures being propagated", "Java returned:");
  }
}
