class PlaceHold {
  public void testFilesetSeparator() {
    executeTarget("testFilesetSeparator");
    assertPropertyContains("testFilesetSeparator", ".xml\",\"");
    assertPropertyEndsWith("testFilesetSeparator", ".xml");
  }
}
