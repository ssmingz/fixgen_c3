class PlaceHold {
  public void test2() {
    executeTarget("test2");
    File f = new File(getProjectDir(), "copytest1dir/copy.xml");
    if (!f.exists()) {
      fail("Copy failed");
    }
  }
}
