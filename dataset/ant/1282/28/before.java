class PlaceHold {
  public void testResultPropertyNonZeroNoFork() {
    executeTarget("testResultPropertyNonZeroNoFork");
    assertEquals("-1", project.getProperty("exitcode"));
  }
}
