class PlaceHold {
  @Test
  public void testSimpleScaleWithMapper() {
    buildRule.executeTarget("testSimpleScaleWithMapper");
    AntAssert.assertContains("Processing File", buildRule.getLog());
    File f = new File(buildRule.getOutputDir(), "scaled-" + LARGEIMAGE);
    assertTrue("Did not create " + f.getAbsolutePath(), f.exists());
  }
}
