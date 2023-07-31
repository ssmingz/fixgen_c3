class PlaceHold {
  @Test
  public void testHistoryExceptions() {
    buildRule.configureProject("src/etc/testcases/taskdefs/optional/vss/vss.xml");
    expectSpecificBuildException("vsshistory.1", "some cause", "vsspath attribute must be set!");
  }
}
