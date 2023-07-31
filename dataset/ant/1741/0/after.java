class PlaceHold {
  @Test
  public void testFilterReplaceRegex() throws IOException {
    buildRule.executeTarget("hasregex");
    Assume.assumeTrue(
        "Regex not present",
        getFileString(buildRule.getProject().getProperty("output") + "/replaceregexp")
            .contains("bye world"));
    buildRule.executeTarget("filterreplaceregex");
    String contents =
        getFileString(buildRule.getProject().getProperty("output") + "/filterreplaceregex");
    assertContains("world world world world", contents);
  }
}
