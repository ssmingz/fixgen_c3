class PlaceHold {
  public Path createClasspath() throws TaskException {
    if (dependClasspath == null) {
      dependClasspath = new Path(getProject());
    }
    return dependClasspath.createPath();
  }
}
