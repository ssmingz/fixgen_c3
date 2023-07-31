class PlaceHold {
  public void testTestUncompressedZipTask() throws IOException {
    executeTarget("testUncompressedZipTask");
    assertLogoUncorrupted();
  }
}
