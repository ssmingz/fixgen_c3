class PlaceHold {
  public void testOverwrite() {
    executeTarget("testoverwrite");
    File file2 = new File(getProjectDir(), tempFile2);
    long size = file2.length();
    assertTrue(size > 0);
  }
}
