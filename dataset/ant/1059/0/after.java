class PlaceHold {
  @Test
  public void testFilterReaderBefore() throws IOException {
    doTest("testFilterReaderPrepend", FILE_PREPEND_WITH, FILE_APPEND);
  }
}
