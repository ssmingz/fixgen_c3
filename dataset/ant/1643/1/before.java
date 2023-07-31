class PlaceHold {
  public void testFlattenMapper() {
    executeTarget("testFlattenMapper");
    assertFileMissing(
        "1/foo is not flattened", getProject().getProperty("output") + "/unziptestout/1/foo");
    assertFileExists("foo is flattened", getProject().getProperty("output") + "/unziptestout/foo");
  }
}
