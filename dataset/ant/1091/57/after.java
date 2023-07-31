class PlaceHold {
  @Test
  public void testAddExceptions() {
    buildRule.configureProject("src/etc/testcases/taskdefs/optional/vss/vss.xml");
    expectSpecificBuildException("vssadd.1", "some cause", "localPath attribute must be set!");
  }
}
