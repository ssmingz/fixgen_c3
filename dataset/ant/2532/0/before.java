class PlaceHold {
  public void test5() {
    configureProject("src/etc/testcases/core/include/frag#ment/relative.xml");
    expectLog("test1", "from included entity");
  }
}
