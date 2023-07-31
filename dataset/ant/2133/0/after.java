class PlaceHold {
  @Test
  public void testOrFails() {
    buildRule.executeTarget("orfails");
    assertNull(buildRule.getProject().getProperty("orfails"));
  }
}
