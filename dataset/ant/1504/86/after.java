class PlaceHold {
  public Path createClasspath() throws TaskException {
    if (classpath == null) {
      classpath = new Path(getProject());
    }
    return classpath.createPath();
  }
}
