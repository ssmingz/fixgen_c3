class PlaceHold {
  public void testCheckinExceptions() {
    configureProject("src/etc/testcases/taskdefs/optional/vss/vss.xml");
    expectSpecificBuildException("vsscheckin.1", "some cause", "vsspath attribute must be set!");
  }
}
