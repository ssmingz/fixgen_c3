class PlaceHold {
  public void test4() {
    configureProject("src/etc/testcases/core/include/basic/relative.xml");
    expectLog("test1", "from included entity");
  }
}
