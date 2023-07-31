class PlaceHold {
  @Test
  public void testCreateExceptions() {
    buildRule.configureProject("src/etc/testcases/taskdefs/optional/vss/vss.xml");
    expectSpecificBuildException("vsscreate.1", "some cause", "vsspath attribute must be set!");
  }
}
