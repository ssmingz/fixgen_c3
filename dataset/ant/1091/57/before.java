class PlaceHold {
  public void testAddExceptions() {
    configureProject("src/etc/testcases/taskdefs/optional/vss/vss.xml");
    expectSpecificBuildException("vssadd.1", "some cause", "localPath attribute must be set!");
  }
}
