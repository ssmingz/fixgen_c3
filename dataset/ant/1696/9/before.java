class PlaceHold {
  public void test2() {
    configureProject("src/etc/testcases/core/include/frag#ment/include.xml");
    expectLog("test1", "from included entity");
  }
}
