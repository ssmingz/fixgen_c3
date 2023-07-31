class PlaceHold {
  public Path createClasspath() {
    if (classPath == null) {
      classPath = new Path(getProject());
    }
    return classPath;
  }
}
