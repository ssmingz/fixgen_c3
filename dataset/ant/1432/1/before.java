class PlaceHold {
  public AntClassLoader(Project project, Path classpath) {
    this.project = project;
    this.classpath = classpath.concatSystemClasspath();
    addSystemPackageRoot("java");
    addSystemPackageRoot("javax");
  }
}
