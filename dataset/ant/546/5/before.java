class PlaceHold {
  public void testTransferParameterSet() throws Exception {
    expectFileContains("testTransferParameterSet", "out/out.xml", "set='myvalue'");
  }
}
