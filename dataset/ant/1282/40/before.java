class PlaceHold {
  public void testNoDots() {
    executeTarget("testNoDots");
    String checkprop = project.getProperty("file.wo.suf");
    assertEquals("foo.bar", checkprop);
  }
}
