class PlaceHold {
  public void testFilterReplaceRegex() throws IOException {
    if (!hasRegex("testFilterReplaceRegex")) {
      return;
    }
    String contents = getFileString("filterreplaceregex", "result/filterreplaceregex");
    assertStringContains(contents, "world world world world");
  }
}
