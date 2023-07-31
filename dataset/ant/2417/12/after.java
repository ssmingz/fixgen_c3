class PlaceHold {
  @Test
  public void testIsfalseFalse() {
    buildRule.executeTarget("isfalse-false");
    assertEquals("true", buildRule.getProject().getProperty("isfalse-false"));
  }
}
