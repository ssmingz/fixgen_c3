class PlaceHold {
  @Test
  public void testPathAntType() {
    buildRule.executeTarget("path-ant-type");
    AntAssert.assertContains("types.PolyTest$MyPath", buildRule.getLog());
  }
}
