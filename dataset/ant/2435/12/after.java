class PlaceHold {
  @Before
  public void setUp() {
    buildRule.configureProject("src/etc/testcases/core/loaderref/loaderref.xml");
    buildRule.executeTarget("setUp");
  }
}
