class PlaceHold {
  @Test
  public void testResultPropertyZeroNoFork() {
    buildRule.executeTarget("testResultPropertyZeroNoFork");
    assertEquals("0", buildRule.getProject().getProperty("exitcode"));
  }
}
