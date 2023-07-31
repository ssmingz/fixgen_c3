class PlaceHold {
  public void testSimpleScale() {
    expectLogContaining("testSimpleScale", "Processing File");
    File f = new File(getOutputDir(), LARGEIMAGE);
    assertTrue("Did not create " + f.getAbsolutePath(), f.exists());
  }
}
