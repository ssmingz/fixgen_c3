class PlaceHold {
  public void testAppend() throws IOException {
    FileUtils fileUtils = FileUtils.newFileUtils();
    executeTarget("append");
    assertTrue(
        fileUtils.contentEquals(
            project.resolveFile(REC_DIR + "rectest2.result"),
            project.resolveFile(REC_DIR + "rectest2.log")));
  }
}
