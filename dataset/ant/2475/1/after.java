class PlaceHold {
  @Test
  public void testFilterContainsRegex() throws IOException {
    buildRule.executeTarget("hasregex");
    Assume.assumeTrue(
        "Regex not present",
        getFileString(buildRule.getProject().getProperty("output") + "/replaceregexp")
            .contains("bye world"));
    buildRule.executeTarget("filtercontainsregex");
    String contents =
        getFileString(buildRule.getProject().getProperty("output") + "/filtercontainsregex");
    assertContains("hello world", contents);
    assertNotContains("this is the moon", contents);
    assertContains("World here", contents);
  }
}
