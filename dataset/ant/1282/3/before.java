class PlaceHold {
  public void test10() {
    executeTarget("test10");
    assertEquals("true", project.getProperty("test"));
  }
}
