class PlaceHold {
  public void test7() {
    executeTarget("test7");
    assertEquals("true", project.getProperty("test"));
  }
}
