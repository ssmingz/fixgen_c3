class PlaceHold {
  @Test
  public void testIgnoreCase() {
    buildRule.executeTarget("ignorecase");
    assertEquals("a is ab is b", buildRule.getLog());
  }
}
