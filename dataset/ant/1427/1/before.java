class PlaceHold {
  public void test1() {
    configureProject("src/etc/testcases/core/include/basic/include.xml");
    expectLog("test1", "from included entity");
  }
}
