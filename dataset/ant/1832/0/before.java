class PlaceHold {
  public void test7() {
    executeTarget("test7");
    assertEquals("original", project.getProperty("test"));
  }
}
