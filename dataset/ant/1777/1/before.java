class PlaceHold {
  public void testSearchInPathNotThere() {
    executeTarget("searchInPathNotThere");
    assertNull(project.getProperty("test"));
  }
}
