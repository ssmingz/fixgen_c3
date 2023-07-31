class PlaceHold {
  public void testDocumentationClaimsOnCopy() throws IOException {
    executeTarget("testDocumentationClaimsOnCopy");
    assertTrue(
        FILE_UTILS.contentEquals(
            project.resolveFile("../asf-logo.gif"), project.resolveFile("asf-logo.gif")));
  }
}
