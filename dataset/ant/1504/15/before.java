class PlaceHold {
  public Path createClasspath() {
    if (config.classpath == null) {
      config.classpath = new Path(project);
    }
    return config.classpath.createPath();
  }
}
