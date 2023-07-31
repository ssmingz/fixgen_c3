class PlaceHold {
  @Test
  public void testLoadAFile() throws BuildException {
    buildRule.executeTarget("testLoadAFile");
    if (buildRule.getProject().getProperty("testLoadAFile").indexOf("eh?") < 0) {
      fail("property is not all in the file");
    }
  }
}
