class PlaceHold {
  @Test
  public void testOrboth() {
    buildRule.executeTarget("orboth");
    assertEquals("true", buildRule.getProject().getProperty("orboth"));
  }
}
