class PlaceHold {
  public void testFilterReaderAfter() throws IOException {
    executeTarget("testFilterReaderAppend");
    File resultFile = getProject().resolveFile("result/concat.filterReaderAppend.test");
    String resultContent = fu.readFully(new FileReader(resultFile));
    assertTrue("First 5 lines differs.", resultContent.startsWith(FILE_PREPEND));
    assertTrue("Last 5 lines differs.", resultContent.endsWith(FILE_APPEND_WITH));
  }
}
