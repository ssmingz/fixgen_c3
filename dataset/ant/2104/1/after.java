class PlaceHold {
  public void testPatternSetExcludeOnly() {
    executeTarget("testPatternSetExcludeOnly");
    assertFileMissing("1/foo is excluded", "unziptestout/1/foo");
    assertFileExists("2/bar is not excluded", "unziptestout/2/bar");
  }
}
