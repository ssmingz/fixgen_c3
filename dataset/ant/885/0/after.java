class PlaceHold {
  @Test
  public void testSingleFileFileset() {
    buildRule.executeTarget("test_single_file_fileset");
    File file =
        new File(buildRule.getProject().getProperty("output"), "copytest_single_file_fileset.tmp");
    assertTrue(file.exists());
  }
}
