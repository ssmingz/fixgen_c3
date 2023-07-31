class PlaceHold {
  @Test
  public void testTimezoneGet() {
    CountLogListener log = new CountLogListener("(\\d+) files? retrieved");
    buildRule.getProject().executeTarget("timed.test.setup");
    buildRule.getProject().addBuildListener(log);
    buildRule.getProject().executeTarget("timed.test.get.older");
    assertEquals(3, log.getCount());
  }
}
