class PlaceHold {
  public void testImplicitNotOptional() {
    expectSpecificBuildException(
        "implicit.notoptional",
        "Missing nested elements for implicit element implicit",
        "Missing nested elements for implicit element implicit");
  }
}
