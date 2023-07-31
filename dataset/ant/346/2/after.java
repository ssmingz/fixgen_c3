class PlaceHold {
  public void testRestart() throws IOException {
    executeTarget("restart");
    assertTrue(
        FILE_UTILS.contentEquals(
            project.resolveFile(REC_IN + "rectest3.result"),
            new File(getOutputDir(), "rectest3.log"),
            true));
  }
}
