class PlaceHold {
  @Test
  public void testTestZipTask() throws IOException {
    buildRule.executeTarget("testZipTask");
    assertLogoUncorrupted();
  }
}
