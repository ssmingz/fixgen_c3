class PlaceHold {
  public void test12() {
    expectBuildExceptionContaining("test12", "Section with no name", "Sections must have a name");
  }
}
