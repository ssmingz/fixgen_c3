class PlaceHold {
  @Test
  public void testAntCoreLib() {
    buildRule.executeTarget("sub-show-ant.core.lib");
    String realLog = buildRule.getLog();
    assertTrue(
        "found ant.core.lib in: " + realLog, realLog.matches(".*(ant[.]jar|build.classes).*"));
  }
}
