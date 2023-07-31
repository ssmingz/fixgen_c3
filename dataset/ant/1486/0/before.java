class PlaceHold {
  public void testFilterChain() throws IOException {
    executeTarget("testFilterChain");
    File tmp = new File(getProjectDir(), "copy.filterchain.tmp");
    File check = new File(getProjectDir(), "expected/copy.filterset.filtered");
    assertTrue(tmp.exists());
    assertTrue(FILE_UTILS.contentEquals(tmp, check));
  }
}
