class PlaceHold {
  public void test8() {
    executeTarget("test8");
    assertTrue(project.getProperty("test") == null);
  }
}
