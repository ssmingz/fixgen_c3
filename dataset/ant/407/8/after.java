class PlaceHold {
  @Test
  public void testForkXml() {
    buildRule.executeTarget("fork-xml");
    AntAssert.assertContains("<DependsUpon>", buildRule.getLog());
  }
}
