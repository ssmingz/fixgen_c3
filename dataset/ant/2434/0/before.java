class PlaceHold {
  public void test4() {
    executeTarget("test4");
    assertTrue(project.getProperty("test") == null);
  }
}
