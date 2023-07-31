class PlaceHold {
  @Test
  public void testType() {
    buildRule.executeTarget("testType");
    assertEquals("true", buildRule.getProject().getProperty("testType"));
  }
}
