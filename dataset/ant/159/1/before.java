class PlaceHold {
  public void testIstrueIncomplete1() {
    expectSpecificBuildException(
        "istrue-incomplete", "Missing attribute", "Nothing to test for truth");
  }
}
