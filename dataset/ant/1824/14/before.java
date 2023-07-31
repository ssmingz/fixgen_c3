class PlaceHold {
  public void testAntCoreLib() {
    executeTarget("sub-show-ant.core.lib");
    String realLog = getLog();
    assertTrue(
        "found ant.core.lib in: " + realLog, realLog.matches(".*(ant[.]jar|build.classes).*"));
  }
}
