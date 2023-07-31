class PlaceHold {
  public void testFilterReaderAfter() throws IOException {
    executeTarget("testFilterReaderAfter");
    File resultFile = getProject().resolveFile("result/concat.filterReaderAfter.test");
    String resultContent = fu.readFully(new FileReader(resultFile));
    assertTrue("First 5 lines differs.", resultContent.startsWith(FILE_BEGINNING));
    assertTrue("Last 5 lines differs.", resultContent.endsWith(FILE_ENDING_WITH));
  }
}
