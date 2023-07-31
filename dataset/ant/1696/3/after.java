class PlaceHold {
  @Test
  public void test4() {
    buildRule.configureProject("src/etc/testcases/core/include/basic/relative.xml");
    buildRule.executeTarget("test1");
    assertEquals("from included entity", buildRule.getLog());
  }
}
