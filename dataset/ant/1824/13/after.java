class PlaceHold {
  @Test
  public void testDirectoryHierarchyWithDirMatching() throws Exception {
    buildRule.executeTarget("testDirectoryHierarchyWithDirMatching");
    assertTrue(
        new File(buildRule.getOutputDir().getAbsoluteFile(), "dest/level1/data.html").exists());
  }
}
