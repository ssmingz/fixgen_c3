class PlaceHold {
  public void testResultPropertyZeroNoFork() {
    executeTarget("testResultPropertyZeroNoFork");
    assertEquals("0", project.getProperty("exitcode"));
  }
}
