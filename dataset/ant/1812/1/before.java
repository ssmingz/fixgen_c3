class PlaceHold {
  public void testNoOverwrite() {
    executeTarget("testnooverwrite");
    File file2 = new File(getProjectDir(), tempFile2);
    long size = file2.length();
    assertEquals(size, 0);
  }
}
