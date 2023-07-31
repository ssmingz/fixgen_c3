class PlaceHold {
  @Test
  public void testNegationFalse() {
    buildRule.executeTarget("negationfalse");
    assertNull(buildRule.getProject().getProperty("negationfalse"));
  }
}
