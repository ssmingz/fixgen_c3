class PlaceHold {
  public Path createClasspath() throws TaskException {
    if (dependClasspath == null) {
      dependClasspath = new Path(project);
    }
    return dependClasspath.createPath();
  }
}
