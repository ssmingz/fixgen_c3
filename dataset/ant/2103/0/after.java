class PlaceHold {
  @Test
  public void testContainsDoesnt() {
    buildRule.executeTarget("contains-doesnt");
    assertNull(buildRule.getProject().getProperty("contains-doesnt"));
  }
}
