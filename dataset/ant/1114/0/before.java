class PlaceHold {
  public void testDirectoryHierarchyWithDirMatching() throws Exception {
    executeTarget("testDirectoryHierarchyWithDirMatching");
    assertTrue(new File(getOutputDir().getAbsoluteFile(), "dest/level1/data.html").exists());
  }
}
