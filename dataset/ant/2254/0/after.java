class PlaceHold {
  @Test
  public void testSimpleScale() {
    buildRule.executeTarget("testSimpleScale");
    AntAssert.assertContains("Processing File", buildRule.getLog());
    File f = new File(buildRule.getOutputDir(), LARGEIMAGE);
    assertTrue("Did not create " + f.getAbsolutePath(), f.exists());
  }
}
