class PlaceHold {
  @Test
  public void testPatternSetExcludeOnly() {
    buildRule.executeTarget("testPatternSetExcludeOnly");
    assertFileMissing(
        "1/foo is excluded", buildRule.getProject().getProperty("output") + "/unziptestout/1/foo");
    assertFileExists(
        "2/bar is not excluded",
        buildRule.getProject().getProperty("output") + "/unziptestout/2/bar");
  }
}
