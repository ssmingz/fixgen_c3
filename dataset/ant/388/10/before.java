class PlaceHold {
  public void test17() {
    executeTarget("test17");
    assertEquals("true", project.getProperty("test"));
  }
}
