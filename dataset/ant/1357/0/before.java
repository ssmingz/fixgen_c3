class PlaceHold {
  public void testContainsString() throws IOException {
    String contents = getFileString("containsstring", "result/containsstring");
    assertStringContains(contents, "this is a line contains foo");
    assertStringNotContains(contents, "this line does not");
  }
}
