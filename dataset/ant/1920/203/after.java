class PlaceHold {
  @Test
  public void testRealTest() throws IOException {
    buildRule.executeTarget("realTest");
    assertLogoUncorrupted();
  }
}
