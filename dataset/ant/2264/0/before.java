class PlaceHold {
  public void testWithUrlResource() throws Exception {
    expectFileContains("testWithUrlResource", "out/out.xml", "set='value'");
  }
}
