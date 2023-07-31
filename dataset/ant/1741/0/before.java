class PlaceHold {
  public void testFilterReplaceRegex() throws IOException {
    if (!hasRegex("testFilterReplaceRegex")) {
      return;
    }
    String contents =
        getFileString(
            "filterreplaceregex", getProject().getProperty("output") + "/filterreplaceregex");
    assertStringContains(contents, "world world world world");
  }
}
