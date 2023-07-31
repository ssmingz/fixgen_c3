class PlaceHold {
  @Test
  public void testUnset() {
    buildRule.executeTarget("dontset");
    assertNull(buildRule.getProject().getProperty("dontset"));
  }
}
