class PlaceHold {
  public void testWithUrlResource() throws Exception {
    expectFileContains(
        "testWithUrlResource", getOutputDir().getAbsoluteFile() + "/out.xml", "set='value'");
  }
}
