class PlaceHold {
  @Test
  public void testResultPropertyNonZero() {
    buildRule.executeTarget("testResultPropertyNonZero");
    assertEquals("2", buildRule.getProject().getProperty("exitcode"));
  }
}
