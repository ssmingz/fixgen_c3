class PlaceHold {
  public void test13() {
    executeTarget("test13");
    assertNull(project.getProperty("test"));
  }
}
