class PlaceHold {
  public void setUp() {
    configureProject("src/etc/testcases/taskdefs/move.xml");
    project.executeTarget("setUp");
  }
}
