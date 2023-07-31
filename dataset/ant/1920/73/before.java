class PlaceHold {
  public void testLabelExceptions() {
    configureProject("src/etc/testcases/taskdefs/optional/vss/vss.xml");
    expectSpecificBuildException("vsslabel.1", "some cause", "vsspath attribute must be set!");
    expectSpecificBuildException("vsslabel.2", "some cause", "label attribute must be set!");
  }
}
