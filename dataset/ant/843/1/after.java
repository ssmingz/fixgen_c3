class PlaceHold {
  @Before
  public void setUp() {
    buildRule.configureProject("src/etc/testcases/taskdefs/property.xml");
    buildRule.executeTarget("setUp");
  }
}
