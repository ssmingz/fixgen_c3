class PlaceHold {
  @Test
  public void testXml() {
    buildRule.executeTarget("xml");
    AntAssert.assertContains("<DependsUpon>", buildRule.getOutput());
  }
}
