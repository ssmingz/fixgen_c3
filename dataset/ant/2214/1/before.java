class PlaceHold {
  public void setUp() {
    configureProject(TASKDEFS_DIR + "echoproperties.xml");
    project.setProperty("test.property", "is set");
  }
}
