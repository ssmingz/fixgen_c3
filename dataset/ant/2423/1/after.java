class PlaceHold {
  @Test
  public void testTestUncompressedZipTask() throws IOException {
    buildRule.executeTarget("testUncompressedZipTask");
    assertLogoUncorrupted();
  }
}
