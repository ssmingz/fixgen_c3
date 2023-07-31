class PlaceHold {
  @Test
  public void testWithSpaceSimple() {
    buildRule.configureProject("src/etc/testcases/core/include/with space/simple.xml");
    buildRule.executeTarget("test1");
    assertEquals("from simple buildfile in 'with space'", buildRule.getLog());
  }
}
