class PlaceHold {
  public Path createPath() {
    if (path == null) {
      path = new Path(getProject());
    }
    return path;
  }
}
