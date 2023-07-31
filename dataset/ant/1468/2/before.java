class PlaceHold {
  public Path createClasspath() {
    if (classPath == null) {
      classPath = new Path(this.project);
    }
    return classPath.createPath();
  }
}
