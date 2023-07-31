class PlaceHold {
  @Test
  public void testContainsRegex() throws IOException {
    buildRule.executeTarget("hasregex");
    Assume.assumeTrue(
        "Regex not present",
        getFileString(buildRule.getProject().getProperty("output") + "/replaceregexp")
            .contains("bye world"));
    buildRule.executeTarget("containsregex");
    String contents =
        getFileString(buildRule.getProject().getProperty("output") + "/containsregex");
    assertContains("hello world", contents);
    assertNotContains("this is the moon", contents);
    assertContains("World here", contents);
  }
}
