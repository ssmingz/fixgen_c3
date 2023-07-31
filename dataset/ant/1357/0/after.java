class PlaceHold {
  public void testContainsString() throws IOException {
    String contents =
        getFileString("containsstring", getProject().getProperty("output") + "/containsstring");
    assertStringContains(contents, "this is a line contains foo");
    assertStringNotContains(contents, "this line does not");
  }
}
