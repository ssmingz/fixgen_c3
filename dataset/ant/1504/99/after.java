class PlaceHold {
  public Path createSearchpath() {
    if (searchPath == null) {
      searchPath = new Path(getProject());
    }
    return searchPath;
  }
}
