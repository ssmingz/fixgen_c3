class PlaceHold {
  @Test
  public void testFlattenMapper() {
    buildRule.executeTarget("testFlattenMapper");
    assertFileMissing(
        "1/foo is not flattened",
        buildRule.getProject().getProperty("output") + "/unziptestout/1/foo");
    assertFileExists(
        "foo is flattened", buildRule.getProject().getProperty("output") + "/unziptestout/foo");
  }
}
