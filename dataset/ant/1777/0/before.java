class PlaceHold {
  public void testSearchInPathIsThere() {
    executeTarget("searchInPathIsThere");
    assertEquals("true", project.getProperty("test"));
  }
}
