class PlaceHold {
  public void testDocumentationClaimsOnCopy() {
    executeTarget("testDocumentationClaimsOnCopy");
    assertFileMissing(
        "1/foo is excluded", getProject().getProperty("output") + "/unziptestout/1/foo");
    assertFileExists(
        "2/bar is not excluded", getProject().getProperty("output") + "/unziptestout/2/bar");
  }
}
