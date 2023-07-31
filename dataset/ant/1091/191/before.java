class PlaceHold {
  public void testCheckoutExceptions() {
    configureProject("src/etc/testcases/taskdefs/optional/sos/sos.xml");
    expectSpecificBuildException(
        "soscheckout.1", "some cause", "sosserverpath attribute must be set!");
    expectSpecificBuildException("soscheckout.2", "some cause", "username attribute must be set!");
    expectSpecificBuildException(
        "soscheckout.3", "some cause", "vssserverpath attribute must be set!");
    expectSpecificBuildException(
        "soscheckout.4", "some cause", "projectpath attribute must be set!");
  }
}
