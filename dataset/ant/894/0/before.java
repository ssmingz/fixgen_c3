class PlaceHold {
  public void testFileTokenizer() throws IOException {
    String contents = getFileString("filetokenizer", "result/filetokenizer");
    assertStringContains(contents, "   of words");
    assertStringNotContains(contents, " This is");
  }
}
