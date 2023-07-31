class PlaceHold {
  public void testLibRefs() {
    executeTarget("testlibrefs");
    File f = new File(getOutputDir(), "WEB-INF/lib/war.xml");
    assertTrue("File has been put into lib", f.exists());
  }
}
