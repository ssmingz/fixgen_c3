class PlaceHold {
  @Test
  public void testCustomTokenFilter() throws IOException {
    buildRule.executeTarget("customtokenfilter");
    assertContains(
        "Hello World", getFileString(buildRule.getProject().getProperty("output") + "/custom"));
  }
}
