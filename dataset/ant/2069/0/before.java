class PlaceHold {
  public void testNested1() {
    expectSpecificBuildException(
        "testNested1", "it is required to fail :-)", "condition satisfied");
  }
}
