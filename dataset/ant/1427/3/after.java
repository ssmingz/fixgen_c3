class PlaceHold {
  @Test
  public void testCalledFromTargetLevelAnt() {
    buildRule.configureProject("src/etc/testcases/core/topleveltasks/targetlevelant.xml");
    buildRule.executeTarget("foo");
    assertEquals("Called", buildRule.getLog());
  }
}
