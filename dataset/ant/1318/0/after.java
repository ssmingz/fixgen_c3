class PlaceHold {
  @Test
  public void testFork() {
    buildRule.executeTarget("fork");
    AntAssert.assertContains("Package: org.apache.tools.ant.util.facade", buildRule.getLog());
  }
}
