class PlaceHold {
  public void testIncompleteFile() throws Exception {
    expectReportWithText("testIncompleteFile", WARNING_IS_POSSIBLY_CORRUPTED);
  }
}
