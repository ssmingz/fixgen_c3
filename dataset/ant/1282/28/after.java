class PlaceHold {
  @Test
  public void testResultPropertyNonZeroNoFork() {
    buildRule.executeTarget("testResultPropertyNonZeroNoFork");
    assertEquals("-1", buildRule.getProject().getProperty("exitcode"));
  }
}
