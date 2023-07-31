class PlaceHold {
  public void testWithFileResource() throws Exception {
    expectFileContains("testWithFileResource", "out/out.xml", "set='value'");
  }
}
