class PlaceHold {
  public void testPatternSetIncludeOnly() {
    executeTarget("testPatternSetIncludeOnly");
    assertTrue("1/foo is not included", !getProject().resolveFile("unziptestout/1/foo").exists());
    assertTrue("2/bar is included", getProject().resolveFile("unziptestout/2/bar").exists());
  }
}
