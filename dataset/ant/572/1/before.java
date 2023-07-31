class PlaceHold {
  public void test9() throws IOException {
    executeTarget("test9");
    assertEqualContent(
        new File(getOutputDir(), "result.txt"), new File(getOutputDir(), "output.txt"));
  }
}
