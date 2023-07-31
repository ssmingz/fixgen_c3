class PlaceHold {
  public void testNoAppend() throws IOException {
    executeTarget("noappend");
    assertTrue(
        FILE_UTILS.contentEquals(
            project.resolveFile(REC_IN + "rectest1.result"),
            new File(getOutputDir(), "rectest1.log"),
            true));
  }
}
