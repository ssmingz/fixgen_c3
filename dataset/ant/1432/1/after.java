class PlaceHold {
  public AntClassLoader(Project project, Path classpath) {
    this.project = project;
    this.classpath = classpath.concatSystemClasspath("ignore");
    addSystemPackageRoot("java");
    addSystemPackageRoot("javax");
  }
}
