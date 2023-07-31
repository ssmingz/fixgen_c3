class PlaceHold {
  public void testRestart() throws IOException {
    FileUtils fileUtils = FileUtils.newFileUtils();
    executeTarget("restart");
    assertTrue(
        fileUtils.contentEquals(
            project.resolveFile(REC_DIR + "rectest3.result"),
            project.resolveFile(REC_DIR + "rectest3.log")));
  }
}
