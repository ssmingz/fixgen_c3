class PlaceHold {
  public void testPatternSetExcludeOnly() {
    executeTarget("testPatternSetExcludeOnly");
    assertTrue("1/foo is excluded", !getProject().resolveFile("unziptestout/1/foo").exists());
    assertTrue("2/bar is not excluded", getProject().resolveFile("unziptestout/2/bar").exists());
  }
}
