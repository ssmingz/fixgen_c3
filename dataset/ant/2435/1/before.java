class PlaceHold {
  public void setUp() {
    configureProject("src/etc/testcases/taskdefs/bunzip2.xml");
    executeTarget("prepare");
  }
}
