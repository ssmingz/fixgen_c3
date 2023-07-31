class PlaceHold {
  @Test
  public void testFilesetSeparator() {
    buildRule.executeTarget("testFilesetSeparator");
    assertPropertyContains("testFilesetSeparator", ".xml\",\"");
    assertPropertyEndsWith("testFilesetSeparator", ".xml");
  }
}
