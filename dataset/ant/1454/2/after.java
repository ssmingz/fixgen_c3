class PlaceHold {
  public void test5() {
    executeTarget("test5");
    File f = new File(getProject().getProperty("output"), "test5.tar");
    if (!f.exists()) {
      fail("Tarring a directory failed");
    }
  }
}
