class PlaceHold {
  public Path createClasspath() {
    if (dependClasspath == null) {
      dependClasspath = new Path(getProject());
    }
    return dependClasspath.createPath();
  }
}
