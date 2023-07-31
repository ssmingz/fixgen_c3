class PlaceHold {
  public void testIso8859_1() throws IOException {
    executeTarget("testIso8859-1");
    File in = getProject().resolveFile("expected/iso8859-1.test");
    File out = getProject().resolveFile("output/iso8859-1.test");
    assertTrue(FileUtils.getFileUtils().contentEquals(in, out, false));
  }
}
