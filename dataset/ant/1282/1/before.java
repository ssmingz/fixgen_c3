class PlaceHold {
  public void testResultPropertyNonZero() {
    executeTarget("testResultPropertyNonZero");
    assertEquals("2", project.getProperty("exitcode"));
  }
}
