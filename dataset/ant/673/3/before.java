class PlaceHold {
  public void testTransferParameterUnset() throws Exception {
    expectFileContains("testTransferParameterUnset", "out/out.xml", "undefined='${value}'");
  }
}
