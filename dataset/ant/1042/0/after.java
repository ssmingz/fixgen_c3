class PlaceHold {
  @Test
  public void testStartTestFired() {
    buildRule.getProject().setProperty("enableEvents", "true");
    buildRule.executeTarget(PASS_TEST_TARGET);
    assertContains(
        "expecting test started message",
        ((JUnitTask.TESTLISTENER_PREFIX + "startTest(") + PASS_TEST) + ")",
        buildRule.getFullLog());
  }
}
