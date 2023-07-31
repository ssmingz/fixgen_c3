class PlaceHold {
  @Test
  public void testTwoPatternSetsWithExcludes() {
    buildRule.executeTarget("testTwoPatternSetsWithExcludes");
    assertFileMissing(
        "1/foo is not included",
        buildRule.getProject().getProperty("output") + "/unziptestout/1/foo");
    assertFileMissing(
        "2/bar is excluded", buildRule.getProject().getProperty("output") + "/unziptestout/2/bar");
  }
}
