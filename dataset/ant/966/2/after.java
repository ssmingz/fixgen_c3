class PlaceHold {
  public void testDeleteRestart() throws IOException {
    executeTarget("deleterestart");
    assertTrue(
        FILE_UTILS.contentEquals(
            project.resolveFile(REC_IN + "rectest4.result"),
            project.resolveFile(REC_DIR + "rectest4.log"),
            true));
  }
}
