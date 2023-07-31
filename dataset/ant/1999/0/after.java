class PlaceHold {
  @Before
  public void setUp() {
    buildRule.configureProject("src/etc/testcases/taskdefs/recorder.xml");
    buildRule.executeTarget("setUp");
  }
}
