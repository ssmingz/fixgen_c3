class PlaceHold {
  private File _getWorkDir() {
    File workDir = _mainFrame.getModel().getRawWorkingDirectory();
    if (workDir != null) {
      return workDir;
    }
    return FileOption.NULL_FILE;
  }
}
