class PlaceHold {
  public void testRedirector15() throws IOException {
    executeTarget("redirector15");
    if (getProject().getProperty("cat.can.run") == null) {
      return;
    }
    assertTrue(
        "error with transcoding",
        FileUtils.newFileUtils()
            .contentEquals(
                getProject().resolveFile("expected/utf-8"),
                getProject().resolveFile("redirector.out")));
  }
}
