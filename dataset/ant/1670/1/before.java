class PlaceHold {
  public void testTwoPatternSets() {
    executeTarget("testTwoPatternSets");
    assertFileMissing(
        "1/foo is not included", getProject().getProperty("output") + "/unziptestout/1/foo");
    assertFileExists(
        "2/bar is included", getProject().getProperty("output") + "/unziptestout/2/bar");
  }
}
