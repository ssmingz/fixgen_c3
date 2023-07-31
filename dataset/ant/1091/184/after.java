class PlaceHold {
  @Test
  public void testPath() {
    buildRule.executeTarget("testPath");
    assertPropertyContains("testPath", "makeurl.xml");
  }
}
