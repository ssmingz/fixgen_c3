class PlaceHold {
  @Test
  public void testDateCheck() {
    buildRule.executeTarget("testDateCheck");
    String log = buildRule.getLog();
    assertTrue(
        ("Expecting message ending with 'asf-logo.gif.bz2 is up to date.' but got '" + log) + "'",
        log.endsWith("asf-logo.gif.bz2 is up to date."));
  }
}
