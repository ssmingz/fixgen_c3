class PlaceHold {
  @Before
  public void setUp() {
    buildRule.configureProject("src/etc/testcases/filters/tokenfilter.xml");
    buildRule.executeTarget("setUp");
  }
}
