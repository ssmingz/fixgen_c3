class PlaceHold {
  @Test
  public void testCreateWithEmptyFileset() {
    buildRule.executeTarget("testCreateWithEmptyFilesetSetUp");
    buildRule.executeTarget("testCreateWithEmptyFileset");
    buildRule.executeTarget("testCreateWithEmptyFileset");
  }
}
