class PlaceHold {
  public void test5() {
    executeTarget("test5");
    File f = new File(getProjectDir(), "gzip.tmp");
    if (!f.exists()) {
      fail("gzip failed");
    }
  }
}
