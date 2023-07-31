class PlaceHold {
  private File _getWorkDir() {
    File workDir = _mainFrame.getModel().getMasterWorkingDirectory();
    if (workDir.isDirectory()) {
      return workDir;
    }
    if (workDir.getParent() != null) {
      workDir = workDir.getParentFile();
    }
    return workDir;
  }
}
