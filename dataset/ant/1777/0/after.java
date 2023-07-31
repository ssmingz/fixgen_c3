class PlaceHold {
  @Test
  public void testSearchInPathIsThere() {
    buildRule.executeTarget("searchInPathIsThere");
    assertEquals("true", buildRule.getProject().getProperty("test"));
  }
}
