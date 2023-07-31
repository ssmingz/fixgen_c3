class PlaceHold {
  public void test5() {
    executeTarget("test5");
    File f = new File(getProjectDir(), "copyfile.tmp");
    if (f.exists()) {
      f.delete();
    } else {
      fail("Copy failed");
    }
  }
}
