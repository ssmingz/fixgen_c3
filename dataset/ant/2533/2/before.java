class PlaceHold {
  public void testNested5() {
    expectSpecificBuildException(
        "testNested5", "it is required to fail :-)", "Only one nested condition is allowed.");
  }
}
