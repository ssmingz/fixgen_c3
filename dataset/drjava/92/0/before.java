class PlaceHold {
  private File _getProjRoot() {
    File projRoot = _model.getProjectRoot();
    if (projRoot != null) {
      return projRoot;
    }
    return FileOption.NULL_FILE;
  }
}
