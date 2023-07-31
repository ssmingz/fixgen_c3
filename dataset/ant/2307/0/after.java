class PlaceHold {
  @Test
  public void testDirsWithSpaces() throws Exception {
    buildRule.executeTarget("testDirsWithSpaces");
    assertTrue(new File(buildRule.getOutputDir().getAbsoluteFile(), "d est/data.html").exists());
  }
}
