class PlaceHold {
  @Before
  public void setUp() {
    buildRule.configureProject("src/etc/testcases/taskdefs/available.xml");
    buildRule.executeTarget("setUp");
  }
}
