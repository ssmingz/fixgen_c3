class PlaceHold {
  @Test
  public void testTimezonePut() {
    CountLogListener log = new CountLogListener("(\\d+) files? sent");
    buildRule.getProject().executeTarget("timed.test.setup");
    buildRule.getProject().addBuildListener(log);
    buildRule.getProject().executeTarget("timed.test.put.older");
    assertEquals(1, log.getCount());
  }
}
