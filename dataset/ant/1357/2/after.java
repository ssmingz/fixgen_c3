class PlaceHold {
  public void testContainsRegex() throws IOException {
    if (!hasRegex("testContainsRegex")) {
      return;
    }
    String contents =
        getFileString("containsregex", getProject().getProperty("output") + "/containsregex");
    assertStringContains(contents, "hello world");
    assertStringNotContains(contents, "this is the moon");
    assertStringContains(contents, "World here");
  }
}
