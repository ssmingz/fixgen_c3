class PlaceHold {
  public void testContainsIncomplete2() {
    expectSpecificBuildException(
        "contains-incomplete2",
        "Missing contains attribute",
        "both string and substring are required in contains");
  }
}
