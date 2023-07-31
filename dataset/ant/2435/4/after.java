class PlaceHold {
  @Before
  public void setUp() {
    buildRule.configureProject("src/etc/testcases/taskdefs/bzip2.xml");
    buildRule.executeTarget("prepare");
  }
}
