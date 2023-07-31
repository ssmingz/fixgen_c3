class PlaceHold {
  @Test
  public void testMultipleTargets2() {
    buildRule.executeTarget("multiple-targets-2");
    assertEquals("dadctb", buildRule.getLog());
  }
}
