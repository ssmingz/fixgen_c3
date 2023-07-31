class PlaceHold {
  @Test
  public void test9() throws IOException {
    buildRule.executeTarget("test9");
    assertEqualContent(
        new File(buildRule.getOutputDir(), "result.txt"),
        new File(buildRule.getOutputDir(), "output.txt"));
  }
}
