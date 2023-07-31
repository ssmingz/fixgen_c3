class PlaceHold {
  @Before
  public void setUp() {
    buildRule.configureProject("src/etc/testcases/filters/dynamicfilter.xml");
    buildRule.executeTarget("setUp");
  }
}
