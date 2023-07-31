class PlaceHold {
  public void testTrimFile() throws IOException {
    String contents = getFileString("trimfile", "result/trimfile");
    assertTrue("no ws at start", contents.startsWith("This is th"));
    assertTrue("no ws at end", contents.endsWith("second line."));
    assertStringContains(contents, "  This is the second");
  }
}
