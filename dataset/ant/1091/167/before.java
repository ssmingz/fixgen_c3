class PlaceHold {
  public void testHistoryExceptions() {
    configureProject("src/etc/testcases/taskdefs/optional/vss/vss.xml");
    expectSpecificBuildException("vsshistory.1", "some cause", "vsspath attribute must be set!");
  }
}
