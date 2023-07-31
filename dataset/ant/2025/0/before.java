class PlaceHold {
  public void testTimezonePut() {
    CountLogListener log = new CountLogListener("(\\d+) files? sent");
    getProject().executeTarget("timed.test.setup");
    getProject().addBuildListener(log);
    getProject().executeTarget("timed.test.put.older");
    assertEquals(1, log.getCount());
  }
}
