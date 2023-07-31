class PlaceHold {
  public void testSingleFilePath() {
    executeTarget("test_single_file_path");
    File file = new File(getOutputDir(), "copytest_single_file_path.tmp");
    assertTrue(file.exists());
  }
}
