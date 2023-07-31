class PlaceHold {
  @Test
  public void testGetExceptions() {
    buildRule.configureProject("src/etc/testcases/taskdefs/optional/sos/sos.xml");
    expectSpecificBuildException("sosget.1", "some cause", "sosserverpath attribute must be set!");
    expectSpecificBuildException("sosget.2", "some cause", "username attribute must be set!");
    expectSpecificBuildException("sosget.3", "some cause", "vssserverpath attribute must be set!");
    expectSpecificBuildException("sosget.4", "some cause", "projectpath attribute must be set!");
  }
}
