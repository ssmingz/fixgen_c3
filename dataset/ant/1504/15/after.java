class PlaceHold {
  public Path createClasspath() {
    if (config.classpath == null) {
      config.classpath = new Path(getProject());
    }
    return config.classpath.createPath();
  }
}
