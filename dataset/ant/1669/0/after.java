class PlaceHold {
  @Test
  public void testTypedMapper() throws Exception {
    assertTrue(!new File(buildRule.getOutputDir().getAbsoluteFile(), "out.xml").exists());
    expectFileContains(
        "testTypedMapper",
        buildRule.getOutputDir().getAbsoluteFile() + "/out.xml",
        "set='myvalue'");
  }
}
