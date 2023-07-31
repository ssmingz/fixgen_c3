class PlaceHold {
  @Before
  public void setUp() {
    buildRule.configureProject("src/etc/testcases/taskdefs/copyfile.xml");
    buildRule.executeTarget("setUp");
  }
}
