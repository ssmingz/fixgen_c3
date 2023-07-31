class PlaceHold {
  public void setUp() {
    configureProject("src/etc/testcases/taskdefs/recorder.xml");
    executeTarget("prepare");
  }
}
