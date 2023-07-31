class PlaceHold {
  @Before
  public void setUp() {
    buildRule.configureProject("src/etc/testcases/core/executor.xml");
    targetCount = 0;
    buildRule.getProject().addBuildListener(this);
  }
}
