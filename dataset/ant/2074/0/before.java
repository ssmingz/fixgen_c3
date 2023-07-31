class PlaceHold {
  public void testFilterReaderBefore() throws IOException {
    executeTarget("testFilterReaderBefore");
    File resultFile = getProject().resolveFile("result/concat.filterReaderBefore.test");
    String resultContent = fu.readFully(new FileReader(resultFile));
    assertTrue("First 5 lines differs.", resultContent.startsWith(FILE_BEGINNING_WITH));
    assertTrue("Last 5 lines differs.", resultContent.endsWith(FILE_ENDING));
  }
}
