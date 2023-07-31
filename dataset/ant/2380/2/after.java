class PlaceHold {
  protected void configureProject(String filename) {
    logBuffer = new StringBuffer();
    fullLogBuffer = new StringBuffer();
    project = new Project();
    project.init();
    project.setUserProperty("ant.file", new File(filename).getAbsolutePath());
    project.addBuildListener(new AntTestListener());
    ProjectHelper.configureProject(project, new File(filename));
  }
}
