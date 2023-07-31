class PlaceHold {
  @Test
  public void tearDown() {
    buildRule.executeTarget("cleanup");
  }
}
