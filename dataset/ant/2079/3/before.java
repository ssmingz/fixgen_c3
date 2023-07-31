class PlaceHold {
  public void test5() {
    executeTarget("test5");
    File f = new File("src/etc/testcases/taskdefs.tmp");
    if ((!f.exists()) || (!f.isDirectory())) {
      fail("Copy failed");
    }
  }
}
