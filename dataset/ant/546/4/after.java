class PlaceHold {
  public void testTransferParameterUnset() throws Exception {
    expectFileContains(
        "testTransferParameterUnset",
        getOutputDir().getAbsoluteFile() + "/out.xml",
        "undefined='${value}'");
  }
}
