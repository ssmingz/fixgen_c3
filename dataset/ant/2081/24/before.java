class PlaceHold {
  public void test9() {
    executeTarget("test9");
    assertEquals("true", project.getProperty("test"));
  }
}
