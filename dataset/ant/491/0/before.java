class PlaceHold {
  public void testTransferParameterSet() throws Exception {
    expectFileContains(
        "testTransferParameterSet", getOutputDir().getAbsoluteFile() + "/out.xml", "set='myvalue'");
  }
}
