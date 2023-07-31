class PlaceHold {
  public Path createClasspath() {
    if (classpath == null) {
      classpath = new Path(project);
    }
    return classpath;
  }
}
