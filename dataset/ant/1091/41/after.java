class PlaceHold {
  @Test
  public void testNewerStylesheet() throws Exception {
    expectFileContains(
        "testNewerStylesheet",
        buildRule.getOutputDir().getAbsoluteFile() + "/out.xml",
        "new-value");
  }
}
