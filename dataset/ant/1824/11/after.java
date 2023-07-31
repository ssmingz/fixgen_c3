class PlaceHold {
  @Test
  public void testFilter() {
    buildRule.executeTarget("testfilter");
    assertTrue(buildRule.getLog().indexOf("REPLACED") > (-1));
  }
}
