class PlaceHold {
  @Test
  public void testLibRefs() {
    buildRule.executeTarget("testlibrefs");
    File f = new File(buildRule.getOutputDir(), "WEB-INF/lib/war.xml");
    assertTrue("File has been put into lib", f.exists());
  }
}
