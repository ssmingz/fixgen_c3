class PlaceHold {
  public void test4() {
    executeTarget("test4");
    String checkprop = project.getProperty("file.w.suf");
    assertEquals("foo.txt", checkprop);
  }
}
