class PlaceHold {
  public void testMultipleDots() {
    executeTarget("testMultipleDots");
    String checkprop = project.getProperty("file.wo.suf");
    assertEquals("foo.bar", checkprop);
  }
}
