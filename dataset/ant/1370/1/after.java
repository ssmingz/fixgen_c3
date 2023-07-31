class PlaceHold {
  @Test
  public void testFullLogOutputMagicProperty() {
    buildRule.getProject().setProperty(ENABLE_TESTLISTENER_EVENTS, "true");
    buildRule.executeTarget(PASS_TEST_TARGET);
    assertContains(
        "expecting full log to have BuildListener events",
        TESTLISTENER_PREFIX,
        buildRule.getFullLog());
  }
}
