class PlaceHold {
  public void test1() {
    executeTarget("test1");
    assertEquals("override", project.getProperty("test"));
  }
}
