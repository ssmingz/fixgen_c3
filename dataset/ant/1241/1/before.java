class PlaceHold {
  public void testContainsRegex() throws IOException {
    if (!hasRegex("testContainsRegex")) {
      return;
    }
    String contents = getFileString("containsregex", "result/containsregex");
    assertStringContains(contents, "hello world");
    assertStringNotContains(contents, "this is the moon");
    assertStringContains(contents, "World here");
  }
}
