class PlaceHold {
  @Before
  public void setUp() {
    buildRule.configureProject("src/etc/testcases/taskdefs/java.xml");
    buildRule.executeTarget("setUp");
    String runFatal = System.getProperty("junit.run.fatal.tests");
    if (runFatal != null) {
      runFatalTests = true;
    }
  }
}
