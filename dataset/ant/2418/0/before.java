class PlaceHold {
  public void testConditionIncomplete() {
    expectSpecificBuildException(
        "condition-incomplete",
        "property attribute has been omitted",
        "The property attribute is required.");
  }
}
