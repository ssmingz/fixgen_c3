class PlaceHold {
  public void test1() {
    executeTarget("test1");
    File f = new File(getProjectDir(), "copytest1.tmp");
    if (!f.exists()) {
      fail("Copy failed");
    }
  }
}
