class PlaceHold {
  public void test15() {
    executeTarget("test15");
    assertNull(project.getProperty("test"));
  }
}
