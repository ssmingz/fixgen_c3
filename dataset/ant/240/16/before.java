class PlaceHold {
  public void testNoVersionInfoFail() {
    expectBuildExceptionContaining(
        "testNoVersionInfoFail",
        "Manifest Implemention information missing.",
        "No Implementation-Title set.");
  }
}
