class PlaceHold {
  @Before
  public void setUp() {
    buildRule.configureProject("src/etc/testcases/types/redirector.xml", MSG_VERBOSE);
  }
}
