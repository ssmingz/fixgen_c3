class PlaceHold {
  public void test3() {
    executeTarget("test3");
    File f = new File(getOutputDir(), "testdir.tmp");
    if ((!f.exists()) || (!f.isDirectory())) {
      fail("mkdir failed");
    } else {
      f.delete();
    }
  }
}
