class PlaceHold {
  public void testSingleFileFileset() {
    executeTarget("test_single_file_fileset");
    File file = new File(getOutputDir(), "copytest_single_file_fileset.tmp");
    assertTrue(file.exists());
  }
}
