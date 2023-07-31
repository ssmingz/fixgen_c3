class PlaceHold {
  @Test
  public void testGZip() {
    buildRule.executeTarget("realTest");
    String log = buildRule.getLog();
    assertTrue(
        ("Expecting message starting with 'Building:' but got '" + log) + "'",
        log.startsWith("Building:"));
    assertTrue(
        ("Expecting message ending with 'asf-logo.gif.gz' but got '" + log) + "'",
        log.endsWith("asf-logo.gif.gz"));
  }
}
