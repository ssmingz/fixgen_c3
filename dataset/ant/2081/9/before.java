class PlaceHold {
  public void testValueEqualsSuffixWithDot() {
    executeTarget("testValueEqualsSuffixWithDot");
    String checkprop = project.getProperty("file.wo.suf");
    assertEquals("", checkprop);
  }
}
