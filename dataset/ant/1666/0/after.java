class PlaceHold {
  @Before
  public void setUp() {
    buildRule.configureProject("src/etc/testcases/taskdefs/calltarget.xml");
    buildRule.executeTarget("setUp");
  }
}
