class PlaceHold {
  public void test5() {
    executeTarget("test5");
    File f = new File(System.getProperty("root"), "src/etc/testcases/taskdefs/test5.tar");
    if (!f.exists()) {
      fail("Tarring a directory failed");
    }
  }
}
