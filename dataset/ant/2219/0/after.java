class PlaceHold {
  @Before
  public void setUp() {
    buildRule.configureProject("src/etc/testcases/taskdefs/bunzip2.xml");
    buildRule.executeTarget("prepare");
  }
}
