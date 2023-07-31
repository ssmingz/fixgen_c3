class PlaceHold {
  @Test
  public void testTransferParameterSet() throws Exception {
    expectFileContains(
        "testTransferParameterSet",
        buildRule.getOutputDir().getAbsoluteFile() + "/out.xml",
        "set='myvalue'");
  }
}
