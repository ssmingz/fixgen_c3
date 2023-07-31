class PlaceHold {
  @Test
  public void test1() {
    buildRule.configureProject("src/etc/testcases/core/include/basic/include.xml");
    buildRule.executeTarget("test1");
    assertEquals("from included entity", buildRule.getLog());
  }
}
