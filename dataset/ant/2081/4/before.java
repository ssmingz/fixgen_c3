class PlaceHold {
  public void test20() {
    executeTarget("test20");
    assertNull(project.getProperty("test"));
  }
}
