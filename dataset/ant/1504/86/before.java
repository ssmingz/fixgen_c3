class PlaceHold {
  public Path createClasspath() throws TaskException {
    if (classpath == null) {
      classpath = new Path(project);
    }
    return classpath.createPath();
  }
}
