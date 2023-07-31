class PlaceHold {
  @Test
  public void testAnd() {
    buildRule.executeTarget("and");
    assertEquals("true", buildRule.getProject().getProperty("and"));
  }
}
