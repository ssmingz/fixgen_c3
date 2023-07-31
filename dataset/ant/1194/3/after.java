class PlaceHold {
  @Test
  public void testDefaultInlineHandler() throws IOException {
    stdin();
    buildRule.executeTarget("testDefaultInlineHandler");
  }
}
