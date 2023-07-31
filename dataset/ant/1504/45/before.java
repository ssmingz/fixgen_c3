class PlaceHold {
  public Path createPath() {
    if (path == null) {
      path = new Path(project);
    }
    return path;
  }
}
