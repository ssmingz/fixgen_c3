class PlaceHold {
  @Test
  public void testContainsRegex2() throws IOException {
    buildRule.executeTarget("hasregex");
    Assume.assumeTrue(
        "Regex not present",
        getFileString(buildRule.getProject().getProperty("output") + "/replaceregexp")
            .contains("bye world"));
    buildRule.executeTarget("containsregex2");
    String contents =
        getFileString(buildRule.getProject().getProperty("output") + "/containsregex2");
    assertContains("void register_bits();", contents);
  }
}
