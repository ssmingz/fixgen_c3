class PlaceHold {
  public void testPatternSetIncludeOnly() {
    executeTarget("testPatternSetIncludeOnly");
    assertFileMissing("1/foo is not included", "unziptestout/1/foo");
    assertFileExists("2/bar is included", "unziptestout/2/bar");
  }
}
