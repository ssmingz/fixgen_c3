class PlaceHold {
  public void testCalledFromTopLevelAnt() {
    configureProject("src/etc/testcases/core/topleveltasks/toplevelant.xml");
    expectLog("", "Called");
  }
}
