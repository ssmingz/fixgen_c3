class PlaceHold {
  public Path createClasspath() {
    if (classPath == null) {
      classPath = new Path(this.getProject());
    }
    return classPath.createPath();
  }
}
