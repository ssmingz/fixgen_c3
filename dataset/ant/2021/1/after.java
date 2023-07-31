class PlaceHold {
  public void testTranscoding() throws IOException {
    executeTarget("testTranscoding");
    File f1 = getProject().resolveFile("copy/expected/utf-8");
    File f2 = new File(getOutputDir(), "copytest1.tmp");
    assertTrue(FILE_UTILS.contentEquals(f1, f2));
  }
}
