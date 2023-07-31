class PlaceHold {
  public Path createSourcespath() {
    if (_sourcesPath == null) {
      _sourcesPath = new Path(getProject());
    }
    return _sourcesPath.createPath();
  }
}
