class PlaceHold {
  public void testResultPropertyZero() {
    executeTarget("testResultPropertyZero");
    assertEquals("0", project.getProperty("exitcode"));
  }
}
