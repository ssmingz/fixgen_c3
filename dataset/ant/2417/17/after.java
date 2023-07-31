class PlaceHold {
  @Test
  public void testIsfalseNot() {
    buildRule.executeTarget("isfalse-not");
    assertEquals("true", buildRule.getProject().getProperty("isfalse-not"));
  }
}
