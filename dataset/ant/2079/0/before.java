class PlaceHold {
  public void test5() {
    executeTarget("test5");
    File f = new File("src/etc/testcases/taskdefs/gzip.tmp");
    if (!f.exists()) {
      fail("gzip failed");
    }
  }
}
