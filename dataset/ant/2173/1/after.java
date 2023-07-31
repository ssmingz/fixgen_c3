class PlaceHold {
  @Test
  public void testFullLogOutput() {
    buildRule.getProject().setProperty("enableEvents", "true");
    buildRule.executeTarget(PASS_TEST_TARGET);
    assertContains(
        "expecting full log to have BuildListener events",
        TESTLISTENER_PREFIX,
        buildRule.getFullLog());
  }
}
