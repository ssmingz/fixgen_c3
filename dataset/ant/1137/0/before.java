class PlaceHold {
  public void testTimezoneGet() {
    CountLogListener log = new CountLogListener("(\\d+) files? retrieved");
    getProject().executeTarget("timed.test.setup");
    getProject().addBuildListener(log);
    getProject().executeTarget("timed.test.get.older");
    assertEquals(3, log.getCount());
  }
}
