class PlaceHold {
  @Test
  public void test5() {
    buildRule.executeTarget("test5");
    File f = new File(buildRule.getProject().getProperty("output"), "test5.tar");
    if (!f.exists()) {
      fail("Tarring a directory failed");
    }
  }
}
