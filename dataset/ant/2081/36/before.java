class PlaceHold {
  public void test21() {
    executeTarget("test21");
    assertEquals("true", project.getProperty("test"));
  }
}
