class PlaceHold {
  public void testFilterReaderBefore() throws IOException {
    executeTarget("testFilterReaderPrepend");
    File resultFile = getProject().resolveFile("result/concat.filterReaderPrepend.test");
    String resultContent = fu.readFully(new FileReader(resultFile));
    assertTrue("First 5 lines differs.", resultContent.startsWith(FILE_PREPEND_WITH));
    assertTrue("Last 5 lines differs.", resultContent.endsWith(FILE_APPEND));
  }
}
