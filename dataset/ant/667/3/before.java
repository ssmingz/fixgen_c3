class PlaceHold {
  public void testTransferParameterEmpty() throws Exception {
    expectFileContains(
        "testTransferParameterEmpty", getOutputDir().getAbsoluteFile() + "/out.xml", "empty=''");
  }
}
