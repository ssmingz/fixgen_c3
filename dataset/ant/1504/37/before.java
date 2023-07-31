class PlaceHold {
  public Path createClasspath() {
    if (dependClasspath == null) {
      dependClasspath = new Path(project);
    }
    return dependClasspath.createPath();
  }
}
