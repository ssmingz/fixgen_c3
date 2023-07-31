class PlaceHold {
  public void testDirsWithSpaces() throws Exception {
    executeTarget("testDirsWithSpaces");
    assertTrue(new File(getOutputDir().getAbsoluteFile(), "d est/data.html").exists());
  }
}
