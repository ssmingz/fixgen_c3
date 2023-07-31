class PlaceHold {
  @Test
  public void testMultipleTargets() {
    buildRule.executeTarget("multiple-targets");
    assertEquals("tadadctbdbtc", buildRule.getLog());
  }
}
