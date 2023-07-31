class PlaceHold {
  public void setUp() {
    configureProject("src/etc/testcases/taskdefs/replace.xml");
    project.executeTarget("setUp");
  }
}
