class PlaceHold {
  public void setUp() {
    configureProject(TASKDEFS_DIR + "jspc.xml");
    baseDir = new File(TASKDEFS_DIR);
    outDir = new File(baseDir, "jsp/java");
  }
}
