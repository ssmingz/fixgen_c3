class PlaceHold {
  @Test
  public void testMacro() {
    buildRule.executeTarget("testMacro");
    assertEquals("true", buildRule.getProject().getProperty("testMacro"));
  }
}
