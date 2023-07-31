class PlaceHold {
  public void testDeleteRestart() throws IOException {
    FileUtils fileUtils = FileUtils.newFileUtils();
    executeTarget("deleterestart");
    assertTrue(
        fileUtils.contentEquals(
            project.resolveFile(REC_DIR + "rectest4.result"),
            project.resolveFile(REC_DIR + "rectest4.log")));
  }
}
