class PlaceHold {
  public void test23() {
    executeTarget("test23");
    assertEquals("true", project.getProperty("test"));
  }
}
