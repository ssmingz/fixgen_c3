class PlaceHold {
  public void testTransferParameterUnsetWithIf() throws Exception {
    expectFileContains(
        "testTransferParameterUnsetWithIf", "out/out.xml", "undefined='undefined default value'");
  }
}
