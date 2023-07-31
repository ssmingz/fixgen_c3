class PlaceHold {
  @Test
  public void testWithFileResource() throws Exception {
    expectFileContains(
        "testWithFileResource",
        buildRule.getOutputDir().getAbsoluteFile() + "/out.xml",
        "set='value'");
  }
}
