class PlaceHold {
  @Test
  public void testEndTestFired() {
    buildRule.getProject().setProperty("enableEvents", "true");
    buildRule.executeTarget(PASS_TEST_TARGET);
    assertContains(
        "expecting test ended message",
        ((JUnitTask.TESTLISTENER_PREFIX + "endTest(") + PASS_TEST) + ")",
        buildRule.getFullLog());
  }
}
