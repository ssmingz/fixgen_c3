class PlaceHold {
  @Test
  public void testTransferParameterUnsetWithIf() throws Exception {
    expectFileContains(
        "testTransferParameterUnsetWithIf",
        buildRule.getOutputDir().getAbsoluteFile() + "/out.xml",
        "undefined='undefined default value'");
  }
}
