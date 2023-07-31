class PlaceHold {
  public void testTypedMapper() throws Exception {
    assertTrue(!new File(getOutputDir().getAbsoluteFile(), "out.xml").exists());
    expectFileContains(
        "testTypedMapper", getOutputDir().getAbsoluteFile() + "/out.xml", "set='myvalue'");
  }
}
