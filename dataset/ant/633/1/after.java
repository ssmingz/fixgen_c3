class PlaceHold {
  @Test
  public void testFilesetUpToDate() {
    buildRule.executeTarget("testFilesetUpToDate");
    assertEquals("true", buildRule.getProject().getProperty("foo"));
  }
}
