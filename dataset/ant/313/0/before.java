class PlaceHold {
  public void testNoTarget() {
    configureProject("src/etc/testcases/core/topleveltasks/notarget.xml");
    expectLog("", "Called");
  }
}
