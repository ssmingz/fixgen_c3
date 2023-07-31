class PlaceHold {
  @Test
  public void testCustomMapper() throws Exception {
    assertTrue(!new File(buildRule.getOutputDir().getAbsoluteFile(), "out.xml").exists());
    expectFileContains(
        "testCustomMapper",
        buildRule.getOutputDir().getAbsoluteFile() + "/out.xml",
        "set='myvalue'");
  }
}
