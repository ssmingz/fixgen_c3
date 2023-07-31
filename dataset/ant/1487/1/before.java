class PlaceHold {
  public void testFullLogOutput() {
    getProject().setProperty("enableEvents", "true");
    executeTarget(PASS_TEST_TARGET);
    assertTrue(
        "expecting full log to have BuildListener events", hasBuildListenerEvents(getFullLog()));
  }
}
