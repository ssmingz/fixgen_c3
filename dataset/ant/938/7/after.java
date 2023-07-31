class PlaceHold {
  @Test
  public void testGlobMapper() {
    buildRule.executeTarget("testGlobMapper");
    assertFileMissing(
        "1/foo is not mapped",
        buildRule.getProject().getProperty("output") + "/unziptestout/1/foo");
    assertFileExists(
        "1/foo is mapped",
        buildRule.getProject().getProperty("output") + "/unziptestout/1/foo.txt");
  }
}
