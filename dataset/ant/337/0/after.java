class PlaceHold {
  @Test
  public void test2() {
    buildRule.configureProject("src/etc/testcases/core/include/frag#ment/include.xml");
    buildRule.executeTarget("test1");
    assertEquals("from included entity", buildRule.getLog());
  }
}
