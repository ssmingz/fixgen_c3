class PlaceHold {
  public void testContainsIncomplete1() {
    expectSpecificBuildException(
        "contains-incomplete1",
        "Missing contains attribute",
        "both string and substring are required in contains");
  }
}
