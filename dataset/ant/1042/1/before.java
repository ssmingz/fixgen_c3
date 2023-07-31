class PlaceHold {
  public void testEndTestFired() {
    getProject().setProperty("enableEvents", "true");
    executeTarget(PASS_TEST_TARGET);
    assertTrue(
        "expecting test ended message",
        hasEventMessage(((JUnitTask.TESTLISTENER_PREFIX + "endTest(") + PASS_TEST) + ")"));
  }
}
