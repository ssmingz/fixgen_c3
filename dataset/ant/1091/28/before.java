class PlaceHold {
  public void testLabelExceptions() {
    configureProject("src/etc/testcases/taskdefs/optional/sos/sos.xml");
    expectSpecificBuildException(
        "soslabel.1", "some cause", "sosserverpath attribute must be set!");
    expectSpecificBuildException("soslabel.2", "some cause", "username attribute must be set!");
    expectSpecificBuildException(
        "soslabel.3", "some cause", "vssserverpath attribute must be set!");
    expectSpecificBuildException("soslabel.4", "some cause", "projectpath attribute must be set!");
    expectSpecificBuildException("soslabel.5", "some cause", "label attribute must be set!");
  }
}
