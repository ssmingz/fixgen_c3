class PlaceHold {
  public void testNegationIncomplete() {
    expectSpecificBuildException(
        "negationincomplete", "no conditions in <not>", "You must nest a condition into <not>");
  }
}
