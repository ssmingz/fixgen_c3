class PlaceHold {
  public void test22() {
    executeTarget("test22");
    assertEquals("true", project.getProperty("test"));
  }
}
