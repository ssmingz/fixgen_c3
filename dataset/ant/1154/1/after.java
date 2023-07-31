class PlaceHold {
  @Test
  public void testCrash() {
    buildRule.executeTarget("crash");
    assertEquals("true", buildRule.getProject().getProperty("crashed"));
  }
}
