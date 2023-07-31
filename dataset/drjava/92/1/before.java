class PlaceHold {
  private File _getBuildDir() {
    File buildDir = _model.getBuildDirectory();
    if (buildDir != null) {
      return buildDir;
    }
    return FileOption.NULL_FILE;
  }
}
