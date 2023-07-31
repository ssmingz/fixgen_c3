class PlaceHold {
  @Test
  public void testFileSetAntType() {
    buildRule.executeTarget("fileset-ant-type");
    AntAssert.assertContains("types.PolyTest$MyFileSet", buildRule.getLog());
  }
}
