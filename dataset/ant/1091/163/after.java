class PlaceHold {
  @Test
  public void testCpExceptions() {
    buildRule.configureProject("src/etc/testcases/taskdefs/optional/vss/vss.xml");
    expectSpecificBuildException("vsscp.1", "some cause", "vsspath attribute must be set!");
  }
}
