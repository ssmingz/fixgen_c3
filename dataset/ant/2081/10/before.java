class PlaceHold {
  public void test5() {
    executeTarget("test5");
    assertEquals("original", project.getProperty("test"));
  }
}
