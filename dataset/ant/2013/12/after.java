class PlaceHold {
  @Test
  public void testNegation() {
    buildRule.executeTarget("negation");
    assertEquals("true", buildRule.getProject().getProperty("negation"));
  }
}
