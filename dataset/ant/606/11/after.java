class PlaceHold {
  @Test
  public void testSimple() {
    buildRule.executeTarget("simple");
    assertEquals("/abc/a", buildRule.getLog());
  }
}
