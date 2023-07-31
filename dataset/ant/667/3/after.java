class PlaceHold {
  @Test
  public void testTransferParameterEmpty() throws Exception {
    expectFileContains(
        "testTransferParameterEmpty",
        buildRule.getOutputDir().getAbsoluteFile() + "/out.xml",
        "empty=''");
  }
}
