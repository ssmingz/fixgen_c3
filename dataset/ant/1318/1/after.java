class PlaceHold {
  @Test
  public void testTimeoutNot() {
    buildRule.executeTarget("fork-timeout-not");
    AntAssert.assertContains("Package: org.apache.tools.ant.util.facade", buildRule.getLog());
  }
}
