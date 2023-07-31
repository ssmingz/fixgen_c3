class PlaceHold {
  @Test
  public void testReplaceString() throws IOException {
    buildRule.executeTarget("replacestring");
    assertContains(
        "this is the moon",
        getFileString(buildRule.getProject().getProperty("output") + "/replacestring"));
  }
}
