class PlaceHold {
  public void testGetExceptions() {
    configureProject("src/etc/testcases/taskdefs/optional/vss/vss.xml");
    expectSpecificBuildException("vssget.1", "some cause", "vsspath attribute must be set!");
  }
}
