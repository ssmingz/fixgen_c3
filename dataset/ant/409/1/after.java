class PlaceHold {
  public void testConcatFilterBeforeAfter() throws IOException {
    executeTarget("testConcatFilterPrependAppend");
    File resultFile = getProject().resolveFile("result/concat.concatfilterPrependAppend.test");
    String resultContent = fu.readFully(new FileReader(resultFile));
    assertTrue("First 5 lines differs.", resultContent.startsWith(FILE_PREPEND_WITH));
    assertTrue("Last 5 lines differs.", resultContent.endsWith(FILE_APPEND_WITH));
  }
}
