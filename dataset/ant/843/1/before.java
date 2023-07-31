class PlaceHold {
  public void setUp() {
    configureProject("src/etc/testcases/taskdefs/property.xml");
    project.executeTarget("setUp");
  }
}
