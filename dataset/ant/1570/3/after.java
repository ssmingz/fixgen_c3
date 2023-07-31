class PlaceHold {
  @Test
  public void test3() {
    buildRule.executeTarget("test3");
    File f = new File(buildRule.getProject().getProperty("output"), "testdir.tmp");
    if ((!f.exists()) || (!f.isDirectory())) {
      fail("mkdir failed");
    } else {
      f.delete();
    }
  }
}
