class PlaceHold {
  @Test
  public void testTextTrim() {
    buildRule.executeTarget("text.trim");
    assertContains("[Hello world]", buildRule.getLog());
  }
}
