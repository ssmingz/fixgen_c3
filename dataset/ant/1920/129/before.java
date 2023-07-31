class PlaceHold {
  public void testCheckinExceptions() {
    configureProject("src/etc/testcases/taskdefs/optional/sos/sos.xml");
    expectSpecificBuildException(
        "soscheckin.1", "some cause", "sosserverpath attribute must be set!");
    expectSpecificBuildException("soscheckin.2", "some cause", "username attribute must be set!");
    expectSpecificBuildException(
        "soscheckin.3", "some cause", "vssserverpath attribute must be set!");
    expectSpecificBuildException(
        "soscheckin.4", "some cause", "projectpath attribute must be set!");
  }
}
