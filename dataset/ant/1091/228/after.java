class PlaceHold {
  @Test
  public void testWithUrlResource() throws Exception {
    expectFileContains(
        "testWithUrlResource",
        buildRule.getOutputDir().getAbsoluteFile() + "/out.xml",
        "set='value'");
  }
}
