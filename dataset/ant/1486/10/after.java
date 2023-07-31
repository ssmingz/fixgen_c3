class PlaceHold {
  public void testFilterSet() throws IOException {
    executeTarget("testFilterSet");
    File tmp = new File(getOutputDir(), "move.filterset.tmp");
    File check = new File(getProjectDir(), "expected/copy.filterset.filtered");
    assertTrue(tmp.exists());
    assertTrue(FILE_UTILS.contentEquals(tmp, check));
  }
}
