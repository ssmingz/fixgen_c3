class PlaceHold {
  public void test6() {
    executeTarget("test6");
    assertTrue(project.getProperty("test") == null);
  }
}
