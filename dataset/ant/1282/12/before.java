class PlaceHold {
  public void test24() {
    executeTarget("test24");
    assertEquals("true", project.getProperty("test"));
  }
}
