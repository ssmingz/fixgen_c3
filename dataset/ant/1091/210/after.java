class PlaceHold {
  @Test
  public void testCheckinExceptions() {
    buildRule.configureProject("src/etc/testcases/taskdefs/optional/vss/vss.xml");
    expectSpecificBuildException("vsscheckin.1", "some cause", "vsspath attribute must be set!");
  }
}
