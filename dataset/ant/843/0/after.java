class PlaceHold {
  @Before
  public void setUp() {
    buildRule.configureProject("src/etc/testcases/taskdefs/replace.xml");
    buildRule.executeTarget("setUp");
  }
}
