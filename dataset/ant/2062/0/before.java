class PlaceHold {
  public void testWithSpaceRelative() {
    configureProject("src/etc/testcases/core/include/with space/relative.xml");
    expectLog("test1", "from included entity in 'with space'");
  }
}
