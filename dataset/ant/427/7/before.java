class PlaceHold {
  public void testFilesmatchIncomplete() {
    expectSpecificBuildException(
        "filesmatch-incomplete",
        "Missing file2 attribute",
        "both file1 and file2 are required in filesmatch");
  }
}
