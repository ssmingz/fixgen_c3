class PlaceHold {
  @Test
  public void testEscape() {
    buildRule.executeTarget("escape");
    assertEquals("a@b or a@b is avalue@bvalue", buildRule.getLog());
  }
}
