class PlaceHold {
  public void test16() {
    executeTarget("test16");
    assertEquals("true", project.getProperty("test"));
  }
}
