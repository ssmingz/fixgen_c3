class PlaceHold {
  @Before
  public void setUp() {
    buildRule.configureProject("src/etc/testcases/taskdefs/copydir.xml");
    buildRule.executeTarget("setUp");
  }
}
