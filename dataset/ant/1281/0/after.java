class PlaceHold {
  @Test
  public void test2() {
    buildRule.executeTarget("test2");
    File f = new File(buildRule.getProject().getProperty("output"), "copytest1dir/copy.xml");
    if (!f.exists()) {
      fail("Copy failed");
    }
  }
}
