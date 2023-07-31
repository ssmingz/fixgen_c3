class PlaceHold {
  @Test
  public void testSimple() {
    buildRule.executeTarget("simple");
    AntAssert.assertContains("Package: org.apache.tools.ant.util.facade", buildRule.getOutput());
  }
}
