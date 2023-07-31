class PlaceHold {
  @Test
  public void testReplaceStrings() throws IOException {
    buildRule.executeTarget("replacestrings");
    assertContains("bar bar bar", buildRule.getLog());
  }
}
