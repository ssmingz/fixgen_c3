class PlaceHold {
  public void testNested6() {
    expectSpecificBuildException(
        "testNested6", "it is required to fail :-)", "testNested6\ntestNested6\ntestNested6");
  }
}
