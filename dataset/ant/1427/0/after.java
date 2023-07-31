class PlaceHold {
  @Test
  public void testWithSpaceRelative() {
    buildRule.configureProject("src/etc/testcases/core/include/with space/relative.xml");
    buildRule.executeTarget("test1");
    assertEquals("from included entity in 'with space'", buildRule.getLog());
  }
}
