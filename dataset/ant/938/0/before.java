class PlaceHold {
  public void testTwoPatternSetsWithExcludes() {
    executeTarget("testTwoPatternSetsWithExcludes");
    assertFileMissing(
        "1/foo is not included", getProject().getProperty("output") + "/unziptestout/1/foo");
    assertFileMissing(
        "2/bar is excluded", getProject().getProperty("output") + "/unziptestout/2/bar");
  }
}
