class PlaceHold {
  public void testLoadAFile() throws BuildException {
    executeTarget("testLoadAFile");
    if (project.getProperty("testLoadAFile").indexOf("eh?") < 0) {
      fail("property is not all in the file");
    }
  }
}
