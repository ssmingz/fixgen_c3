class PlaceHold {
  @Test
  public void testPatternSetIncludeOnly() {
    buildRule.executeTarget("testPatternSetIncludeOnly");
    assertFileMissing(
        "1/foo is not included",
        buildRule.getProject().getProperty("output") + "/unziptestout/1/foo");
    assertFileExists(
        "2/bar is included", buildRule.getProject().getProperty("output") + "/unziptestout/2/bar");
  }
}
