class PlaceHold {
  public Path createClasspath() {
    if (classPath == null) {
      classPath = new Path(project);
    }
    return classPath;
  }
}
