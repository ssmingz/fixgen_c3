class PlaceHold {
  public void testCustomMapper() throws Exception {
    assertTrue(!new File(getOutputDir().getAbsoluteFile(), "out.xml").exists());
    expectFileContains(
        "testCustomMapper", getOutputDir().getAbsoluteFile() + "/out.xml", "set='myvalue'");
  }
}
