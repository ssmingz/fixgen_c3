class PlaceHold {
  public void test5() {
    executeTarget("test5");
    File f = new File(getProjectDir(), "../taskdefs.tmp");
    if ((!f.exists()) || (!f.isDirectory())) {
      fail("Copy failed");
    }
  }
}
