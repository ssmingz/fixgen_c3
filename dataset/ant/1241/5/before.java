class PlaceHold {
  public void testFilterContainsRegex() throws IOException {
    if (!hasRegex("testFilterContainsRegex")) {
      return;
    }
    String contents = getFileString("filtercontainsregex", "result/filtercontainsregex");
    assertStringContains(contents, "hello world");
    assertStringNotContains(contents, "this is the moon");
    assertStringContains(contents, "World here");
  }
}
