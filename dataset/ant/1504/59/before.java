class PlaceHold {
  public Path createSourcespath() {
    if (_sourcesPath == null) {
      _sourcesPath = new Path(project);
    }
    return _sourcesPath.createPath();
  }
}
