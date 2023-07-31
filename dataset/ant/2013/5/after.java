class PlaceHold {
  @Test
  public void testContains() {
    buildRule.executeTarget("contains");
    assertEquals("true", buildRule.getProject().getProperty("contains"));
  }
}
