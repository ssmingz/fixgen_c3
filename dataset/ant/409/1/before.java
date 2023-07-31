class PlaceHold {
  public void testConcatFilterBeforeAfter() throws IOException {
    executeTarget("testConcatFilterBeforeAfter");
    File resultFile = getProject().resolveFile("result/concat.concatfilterBeforeAfter.test");
    String resultContent = fu.readFully(new FileReader(resultFile));
    assertTrue("First 5 lines differs.", resultContent.startsWith(FILE_BEGINNING_WITH));
    assertTrue("Last 5 lines differs.", resultContent.endsWith(FILE_ENDING_WITH));
  }
}
