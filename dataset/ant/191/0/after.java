class PlaceHold {
  @Before
  public void setUp() {
    buildRule.configureProject("src/etc/testcases/taskdefs/move.xml");
    buildRule.executeTarget("setUp");
  }
}
