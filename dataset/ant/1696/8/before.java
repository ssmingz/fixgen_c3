class PlaceHold {
  public void testCalledFromTargetLevelAnt() {
    configureProject("src/etc/testcases/core/topleveltasks/targetlevelant.xml");
    expectLog("foo", "Called");
  }
}
