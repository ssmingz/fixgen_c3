class PlaceHold {
  public void testConcatFilterBefore() throws IOException {
    executeTarget("testConcatFilterPrepend");
    File resultFile = getProject().resolveFile("result/concat.concatfilterPrepend.test");
    String resultContent = fu.readFully(new FileReader(resultFile));
    assertTrue("First 5 lines differs.", resultContent.startsWith(FILE_PREPEND_WITH));
    assertTrue("Last 5 lines differs.", resultContent.endsWith(FILE_APPEND));
  }
}
