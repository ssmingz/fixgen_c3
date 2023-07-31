class PlaceHold {
  public void test4() {
    executeTarget("test4");
    assertEquals("original", project.getProperty("test"));
  }
}
