class PlaceHold {
  @Test
  public void testFilterReplaceStrings() throws IOException {
    buildRule.executeTarget("filterreplacestrings");
    assertContains("bar bar bar", buildRule.getLog());
  }
}
