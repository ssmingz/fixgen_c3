class PlaceHold {
  public void testTransferParameterEmpty() throws Exception {
    expectFileContains("testTransferParameterEmpty", "out/out.xml", "empty=''");
  }
}
