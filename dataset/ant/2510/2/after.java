class PlaceHold {
  public void testAppend() throws IOException {
    executeTarget("append");
    assertTrue(
        FILE_UTILS.contentEquals(
            project.resolveFile(REC_IN + "rectest2.result"),
            new File(getOutputDir(), "rectest2.log"),
            true));
  }
}
