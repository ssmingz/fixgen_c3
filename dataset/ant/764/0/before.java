class PlaceHold {
  public void setUp() {
    configureProject("src/etc/testcases/taskdefs/bzip2.xml");
    executeTarget("prepare");
  }
}
