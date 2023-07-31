class PlaceHold {
  public void testNoAppend() throws IOException {
    FileUtils fileUtils = FileUtils.newFileUtils();
    executeTarget("noappend");
    assertTrue(
        fileUtils.contentEquals(
            project.resolveFile(REC_DIR + "rectest1.result"),
            project.resolveFile(REC_DIR + "rectest1.log")));
  }
}
