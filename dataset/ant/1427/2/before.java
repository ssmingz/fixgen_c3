class PlaceHold {
  public void test3() {
    configureProject("src/etc/testcases/core/include/frag#ment/simple.xml");
    expectLog("test1", "from simple buildfile");
  }
}
