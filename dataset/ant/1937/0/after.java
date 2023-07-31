class PlaceHold {
  @Before
  public void setUp() {
    buildRule.configureProject("src/etc/testcases/taskdefs/tar.xml");
    buildRule.executeTarget("setUp");
  }
}
