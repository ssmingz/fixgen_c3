class PlaceHold {
  public void testText() {
    expectSpecificBuildException("testText", "it is required to fail :-)", "testText");
  }
}
