class PlaceHold {
  @Test
  public void testIllegalCombinations() {
    buildRule.executeTarget("testIllegalCombinations");
    assertPropertyContains("testIllegalCombinations", "/foo");
    assertPropertyContains("testIllegalCombinations", ".xml");
  }
}
