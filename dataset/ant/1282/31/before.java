class PlaceHold {
  public void test18() {
    executeTarget("test18");
    assertNull(project.getProperty("test"));
  }
}
