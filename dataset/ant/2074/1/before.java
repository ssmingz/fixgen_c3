class PlaceHold {
  public void testConcatFilterBefore() throws IOException {
    executeTarget("testConcatFilterBefore");
    File resultFile = getProject().resolveFile("result/concat.concatfilterBefore.test");
    String resultContent = fu.readFully(new FileReader(resultFile));
    assertTrue("First 5 lines differs.", resultContent.startsWith(FILE_BEGINNING_WITH));
    assertTrue("Last 5 lines differs.", resultContent.endsWith(FILE_ENDING));
  }
}
