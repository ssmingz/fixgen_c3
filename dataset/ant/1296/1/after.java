class PlaceHold {
  @Test
  public void test1() {
    buildRule.executeTarget("test1");
    File f = new File(buildRule.getProject().getProperty("output"), "copytest1.tmp");
    if (!f.exists()) {
      fail("Copy failed");
    }
  }
}
