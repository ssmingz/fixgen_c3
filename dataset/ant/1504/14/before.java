class PlaceHold {
  public Path createSearchpath() {
    if (searchPath == null) {
      searchPath = new Path(project);
    }
    return searchPath;
  }
}
