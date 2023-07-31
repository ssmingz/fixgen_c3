class PlaceHold {
  public void testTrimFileByLine() throws IOException {
    String contents = getFileString("trimfilebyline", "result/trimfilebyline");
    assertFalse("no ws at start", contents.startsWith("This is th"));
    assertFalse("no ws at end", contents.endsWith("second line."));
    assertStringNotContains(contents, "  This is the second");
    assertStringContains(contents, "file.\nThis is the second");
  }
}
