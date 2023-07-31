class PlaceHold {
  private File _getProjRoot() {
    File projRoot = _mainFrame.getModel().getProjectRoot();
    if (projRoot != null) {
      return projRoot;
    }
    return FileOption.NULL_FILE;
  }
}
