class PlaceHold {
  @Before
  public void setUp() {
    buildRule.configureProject("src/etc/testcases/taskdefs/abstractcvstask.xml");
    buildRule.executeTarget("setUp");
  }
}
