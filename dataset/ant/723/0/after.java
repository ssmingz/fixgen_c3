class PlaceHold {
  @Test
  public void testSeconds() {
    buildRule.executeTarget("seconds");
    getTargetTime();
  }
}
