class PlaceHold {
  @Test
  public void testTextElement() {
    buildRule.executeTarget("textelement");
    assertContains("Hello world", buildRule.getLog());
  }
}
