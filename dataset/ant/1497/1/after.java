class PlaceHold {
  @Test
  public void testFileSet() {
    buildRule.executeTarget("fileset");
    AntAssert.assertContains("types.FileSet", buildRule.getLog());
  }
}
