class PlaceHold {
  public void test11() {
    executeTarget("test11");
    assertNull(project.getProperty("test"));
  }
}
