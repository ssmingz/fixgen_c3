class PlaceHold {
  public void testValueEqualsSuffixWithoutDot() {
    executeTarget("testValueEqualsSuffixWithoutDot");
    String checkprop = project.getProperty("file.wo.suf");
    assertEquals("", checkprop);
  }
}
