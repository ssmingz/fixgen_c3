class PlaceHold {
  public void test5() {
    executeTarget("test5");
    String checkprop = project.getProperty("file.wo.suf");
    assertEquals("foo", checkprop);
  }
}
