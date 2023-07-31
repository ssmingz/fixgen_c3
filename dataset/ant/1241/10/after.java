class PlaceHold {
  public void testFilterReplaceString() throws IOException {
    String contents =
        getFileString(
            "filterreplacestring", getProject().getProperty("output") + "/filterreplacestring");
    assertStringContains(contents, "This is the moon");
  }
}
