class PlaceHold {
  public void testWithSpaceSimple() {
    configureProject("src/etc/testcases/core/include/with space/simple.xml");
    expectLog("test1", "from simple buildfile in 'with space'");
  }
}
