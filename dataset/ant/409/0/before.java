class PlaceHold {
  public void testFilterReaderBeforeAfter() throws IOException {
    executeTarget("testFilterReaderBeforeAfter");
    File resultFile = getProject().resolveFile("result/concat.filterReaderBeforeAfter.test");
    String resultContent = fu.readFully(new FileReader(resultFile));
    assertTrue("First 5 lines differs.", resultContent.startsWith(FILE_BEGINNING_WITH));
    assertTrue("Last 5 lines differs.", resultContent.endsWith(FILE_ENDING_WITH));
  }
}
