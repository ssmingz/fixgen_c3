class PlaceHold {
  @Test
  public void testSearchInPathNotThere() {
    buildRule.executeTarget("searchInPathNotThere");
    assertNull(buildRule.getProject().getProperty("test"));
  }
}
