class PlaceHold {
  @Before
  public void setUp() {
    buildRule.configureProject("src/etc/testcases/taskdefs/copy.xml");
    buildRule.executeTarget("setUp");
  }
}
