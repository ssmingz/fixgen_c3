class PlaceHold {
  @Test
  public void testAndFails() {
    buildRule.executeTarget("andfails");
    assertNull(buildRule.getProject().getProperty("andfails"));
  }
}
