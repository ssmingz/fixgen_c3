class PlaceHold {
  public void testExcluded() {
    expectBuildExceptionContaining(
        "excluded", "excluded uri", "Attempt to use a reserved URI ant:notallowed");
  }
}
