class PlaceHold {
  public void testGlobMapper() {
    executeTarget("testGlobMapper");
    assertFileMissing(
        "1/foo is not mapped", getProject().getProperty("output") + "/unziptestout/1/foo");
    assertFileExists(
        "1/foo is mapped", getProject().getProperty("output") + "/unziptestout/1/foo.txt");
  }
}
