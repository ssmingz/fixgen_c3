class PlaceHold {
  public void testDateCheck() {
    executeTarget("testDateCheck");
    String log = getLog();
    assertTrue(
        ("Expecting message ending with 'asf-logo.gif.bz2 is up to date.' but got '" + log) + "'",
        log.endsWith("asf-logo.gif.bz2 is up to date."));
  }
}
