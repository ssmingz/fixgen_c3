class PlaceHold {
  @Before
  public void setUp() {
    buildRule.configureProject("src/etc/testcases/taskdefs/zip.xml");
    buildRule.executeTarget("setUp");
  }
}
