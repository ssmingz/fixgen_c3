class PlaceHold {
  public void setUp() {
    configureProject(TASKDEFS_DIR + "jspc.xml");
    baseDir = new File(System.getProperty("root"), TASKDEFS_DIR);
    outDir = new File(baseDir, "jsp/java");
  }
}
