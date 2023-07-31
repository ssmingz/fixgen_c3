class PlaceHold {
  public void testCheckoutExceptions() {
    configureProject("src/etc/testcases/taskdefs/optional/vss/vss.xml");
    expectSpecificBuildException("vsscheckout.1", "some cause", "vsspath attribute must be set!");
    expectSpecificBuildException(
        "vsscheckout.2", "some cause", "blah is not a legal value for this attribute");
  }
}
