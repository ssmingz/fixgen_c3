class PlaceHold {
  @Test
  public void test5() {
    buildRule.configureProject("src/etc/testcases/core/include/frag#ment/relative.xml");
    buildRule.executeTarget("test1");
    assertEquals("from included entity", buildRule.getLog());
  }
}
