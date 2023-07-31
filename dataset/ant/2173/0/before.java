class PlaceHold {
  public void testFullLogOutputMagicProperty() {
    getProject().setProperty(ENABLE_TESTLISTENER_EVENTS, "true");
    executeTarget(PASS_TEST_TARGET);
    assertTrue(
        "expecting full log to have BuildListener events", hasBuildListenerEvents(getFullLog()));
  }
}
