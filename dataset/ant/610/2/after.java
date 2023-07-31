class PlaceHold {
  @Test
  public void testSimpleCopy() {
    buildRule.executeTarget("simplecopy");
    String d = buildRule.getProject().getProperty("dest") + "/a/b/c/d";
    assertFileIsPresent(d);
    assertTrue(buildRule.getFullLog().indexOf("dangling") == (-1));
  }
}
