class PlaceHold {
  public void testFilterReplaceString() throws IOException {
    String contents = getFileString("filterreplacestring", "result/filterreplacestring");
    assertStringContains(contents, "This is the moon");
  }
}
