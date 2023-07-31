class PlaceHold {
  public void testSimpleScaleWithMapper() {
    expectLogContaining("testSimpleScaleWithMapper", "Processing File");
    File f = new File(getOutputDir(), "scaled-" + LARGEIMAGE);
    assertTrue("Did not create " + f.getAbsolutePath(), f.exists());
  }
}
