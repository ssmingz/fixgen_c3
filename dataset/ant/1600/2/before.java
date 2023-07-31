class PlaceHold {
  public void testPatternSetIncludeAndExclude() {
    executeTarget("testPatternSetIncludeAndExclude");
    assertFileMissing(
        "1/foo is not included", getProject().getProperty("output") + "/unziptestout/1/foo");
    assertFileMissing(
        "2/bar is excluded", getProject().getProperty("output") + "/unziptestout/2/bar");
  }
}
