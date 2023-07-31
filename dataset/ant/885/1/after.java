class PlaceHold {
  @Test
  public void testSingleFilePath() {
    buildRule.executeTarget("test_single_file_path");
    File file =
        new File(buildRule.getProject().getProperty("output"), "copytest_single_file_path.tmp");
    assertTrue(file.exists());
  }
}
