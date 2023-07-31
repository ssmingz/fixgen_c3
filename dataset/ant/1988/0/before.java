class PlaceHold {
  public void testDocumentationClaimsOnCopy() throws IOException {
    executeTarget("testDocumentationClaimsOnCopy");
    assertTrue(
        "File content mismatch after bunzip2",
        FILE_UTILS.contentEquals(
            project.resolveFile("expected/asf-logo-huge.tar"),
            project.resolveFile("asf-logo-huge.tar")));
  }
}
