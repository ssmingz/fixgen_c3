class PlaceHold {
  @Test
  public void testTopLevelText() {
    buildRule.executeTarget("top-level-text");
    assertContains("Hello World", buildRule.getLog());
  }
}
