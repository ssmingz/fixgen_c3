class PlaceHold {
  public void setUp() {
    configureProject("src/etc/testcases/taskdefs/java.xml");
    project.executeTarget("setUp");
    String runFatal = System.getProperty("junit.run.fatal.tests");
    if (runFatal != null) {
      runFatalTests = true;
    }
  }
}
