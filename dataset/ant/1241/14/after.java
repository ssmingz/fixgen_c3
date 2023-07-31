class PlaceHold {
  public void testFileTokenizer() throws IOException {
    String contents =
        getFileString("filetokenizer", getProject().getProperty("output") + "/filetokenizer");
    assertStringContains(contents, "   of words");
    assertStringNotContains(contents, " This is");
  }
}
