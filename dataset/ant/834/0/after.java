class PlaceHold {
  public void testConcatFilterAfter() throws IOException {
    executeTarget("testConcatFilterAppend");
    File resultFile = getProject().resolveFile("result/concat.concatfilterAppend.test");
    String resultContent = fu.readFully(new FileReader(resultFile));
    assertTrue("First 5 lines differs.", resultContent.startsWith(FILE_PREPEND));
    assertTrue("Last 5 lines differs.", resultContent.endsWith(FILE_APPEND_WITH));
  }
}
