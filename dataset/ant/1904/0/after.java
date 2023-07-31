class PlaceHold {
  @Test
  public void testSrcDirTest() {
    try {
      buildRule.executeTarget("srcDirTest");
      fail("Src cannot be a directory.");
    } catch (BuildException ex) {
    }
  }
}
