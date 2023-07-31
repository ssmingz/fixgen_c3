class PlaceHold {
  @Test
  public void test3() {
    buildRule.configureProject("src/etc/testcases/core/include/frag#ment/simple.xml");
    buildRule.executeTarget("test1");
    assertEquals("from simple buildfile", buildRule.getLog());
  }
}
