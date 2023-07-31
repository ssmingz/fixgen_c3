class PlaceHold {
  public void testFilterReaderBeforeAfter() throws IOException {
    executeTarget("testFilterReaderPrependAppend");
    File resultFile = getProject().resolveFile("result/concat.filterReaderPrependAppend.test");
    String resultContent = fu.readFully(new FileReader(resultFile));
    assertTrue("First 5 lines differs.", resultContent.startsWith(FILE_PREPEND_WITH));
    assertTrue("Last 5 lines differs.", resultContent.endsWith(FILE_APPEND_WITH));
  }
}
