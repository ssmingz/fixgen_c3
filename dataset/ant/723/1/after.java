class PlaceHold {
  @Test
  public void testNoSeconds() {
    buildRule.executeTarget("noSeconds");
    getTargetTime();
  }
}
