class PlaceHold {
  @Test
  public void testAndempty() {
    buildRule.executeTarget("andempty");
    assertEquals("true", buildRule.getProject().getProperty("andempty"));
  }
}
