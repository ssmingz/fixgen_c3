class PlaceHold {
  public void testDirsetPathWithoutPackagenames() throws Exception {
    try {
      executeTarget("dirsetPathWithoutPackagenames");
    } catch (BuildException e) {
      fail("Contents of path should be picked up without specifying package names: " + e);
    }
  }
}
