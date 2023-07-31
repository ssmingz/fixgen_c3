class PlaceHold {
  @Before
  public void setUp() {
    buildRule.configureProject("src/etc/testcases/core/directoryscanner.xml");
    buildRule.getProject().executeTarget("setUp");
  }
}
