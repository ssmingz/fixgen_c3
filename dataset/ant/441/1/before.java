class PlaceHold {
  public void testStartTestFired() {
    getProject().setProperty("enableEvents", "true");
    executeTarget(PASS_TEST_TARGET);
    assertTrue(
        "expecting test started message",
        hasEventMessage(((JUnitTask.TESTLISTENER_PREFIX + "startTest(") + PASS_TEST) + ")"));
  }
}
