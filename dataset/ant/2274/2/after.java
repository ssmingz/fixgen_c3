class PlaceHold {
  @Test
  public void testEchoToBadFile() {
    try {
      buildRule.executeTarget("testEchoToBadFile");
      fail("BuildException should have been thrown on destination file being a directory");
    } catch (BuildException ex) {
      assertContains("destfile is a directory", "destfile is a directory!", ex.getMessage());
    }
  }
}
