class PlaceHold {
  public void testConditionEmpty() {
    expectSpecificBuildException(
        "condition-empty", "no conditions", "You must nest a condition into <condition>");
  }
}
