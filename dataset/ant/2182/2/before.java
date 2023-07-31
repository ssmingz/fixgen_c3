class PlaceHold {
  public void test13b() {
    executeTarget("test13b");
    assertEquals("true", project.getProperty("test"));
  }
}
