class PlaceHold {
  public void testReplaceRegex() throws IOException {
    if (!hasRegex("testReplaceRegex")) {
      return;
    }
    String contents =
        getFileString("replaceregex", getProject().getProperty("output") + "/replaceregex");
    assertStringContains(contents, "world world world world");
    assertStringContains(contents, "dog Cat dog");
    assertStringContains(contents, "moon Sun Sun");
    assertStringContains(contents, "found WhiteSpace");
    assertStringContains(contents, "Found digits [1234]");
    assertStringNotContains(contents, "This is a line with digits");
  }
}
