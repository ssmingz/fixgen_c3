class PlaceHold {
  private File _getWorkDir() {
    File workDir = _mainFrame.getModel().getMasterWorkingDirectory();
    assert workDir != null;
    if (workDir.isDirectory()) {
      return workDir;
    }
    if (workDir.getParent() != null) {
      workDir = workDir.getParentFile();
    }
    return workDir;
  }
}
