class PlaceHold {
  public void testIllegalCombinations() {
    executeTarget("testIllegalCombinations");
    assertPropertyContains("testIllegalCombinations", "/foo");
    assertPropertyContains("testIllegalCombinations", ".xml");
  }
}
