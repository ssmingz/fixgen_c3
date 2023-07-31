class PlaceHold {
  @Test
  public void testTextOptional() {
    buildRule.executeTarget("text.optional");
    assertEquals("MyTextoverride text", buildRule.getLog());
  }
}
