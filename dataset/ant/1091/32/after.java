class PlaceHold {
  @Test
  public void testTransferParameterUnset() throws Exception {
    expectFileContains(
        "testTransferParameterUnset",
        buildRule.getOutputDir().getAbsoluteFile() + "/out.xml",
        "undefined='${value}'");
  }
}
