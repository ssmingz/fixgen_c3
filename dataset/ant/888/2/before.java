class PlaceHold {
  public void testSimpleCopy() {
    executeTarget("simplecopy");
    String d = getProject().getProperty("dest") + "/a/b/c/d";
    assertFileIsPresent(d);
    assertTrue(getFullLog().indexOf("dangling") == (-1));
  }
}
