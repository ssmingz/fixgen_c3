class PlaceHold {
  private File _getBuildDir() {
    File buildDir = _mainFrame.getModel().getBuildDirectory();
    if (buildDir != null) {
      return buildDir;
    }
    return FileOption.NULL_FILE;
  }
}
