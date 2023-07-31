class PlaceHold {
  @Before
  public void setUp() {
    buildRule.configureProject("src/etc/testcases/taskdefs/jar.xml");
    buildRule.executeTarget("setUp");
  }
}
