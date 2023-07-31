class PlaceHold {
  @Test
  public void testCalledFromTopLevelAnt() {
    buildRule.configureProject("src/etc/testcases/core/topleveltasks/toplevelant.xml");
    buildRule.executeTarget("");
    assertEquals("Called", buildRule.getLog());
  }
}
