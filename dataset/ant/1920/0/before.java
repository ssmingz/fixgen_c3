class PlaceHold {
  public void testWithFileResource() throws Exception {
    expectFileContains(
        "testWithFileResource", getOutputDir().getAbsoluteFile() + "/out.xml", "set='value'");
  }
}
