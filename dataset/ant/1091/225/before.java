class PlaceHold {
  public void testCreateExceptions() {
    configureProject("src/etc/testcases/taskdefs/optional/vss/vss.xml");
    expectSpecificBuildException("vsscreate.1", "some cause", "vsspath attribute must be set!");
  }
}
