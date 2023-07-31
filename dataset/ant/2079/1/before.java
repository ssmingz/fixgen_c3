class PlaceHold {
  public void test5() {
    executeTarget("test5");
    File f = new File("src/etc/testcases/taskdefs/copyfile.tmp");
    if (f.exists()) {
      f.delete();
    } else {
      fail("Copy failed");
    }
  }
}
