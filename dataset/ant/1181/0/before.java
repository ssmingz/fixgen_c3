class PlaceHold {
  public void testNoJarNoClassname() {
    expectBuildExceptionContaining(
        "testNoJarNoClassname", "parameter validation", "Classname must not be null.");
  }
}
