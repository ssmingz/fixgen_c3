class PlaceHold {
  private void _newProject() {
    _closeProject(true);
    _saveChooser.setFileFilter(_projectFilter);
    _saveChooser.setMultiSelectionEnabled(false);
    int rc = _saveChooser.showSaveDialog(this);
    if (rc == JFileChooser.APPROVE_OPTION) {
      File projectFile = _saveChooser.getSelectedFile();
      if ((projectFile == null) || (projectFile.getParentFile() == null)) {
        return;
      }
      String fileName = projectFile.getName();
      if (!fileName.endsWith(PROJECT_FILE_EXTENSION)) {
        int lastIndex = fileName.lastIndexOf(".");
        if (lastIndex == (-1)) {
          projectFile =
              new File(projectFile.getAbsolutePath() + OptionConstants.PROJECT_FILE_EXTENSION);
        } else {
          projectFile =
              new File(
                  projectFile.getParentFile(),
                  fileName.substring(0, lastIndex) + OptionConstants.PROJECT_FILE_EXTENSION);
        }
      }
      if (((projectFile == null) || (projectFile.getParentFile() == null))
          || (projectFile.exists() && (!_verifyOverwrite(projectFile)))) {
        return;
      }
      _model.createNewProject(projectFile);
      _editProject();
      try {
        _model.configNewProject();
      } catch (IOException e) {
        throw new UnexpectedException(e);
      }
      _setUpProjectButtons(projectFile);
      _currentProjFile = projectFile;
    }
  }
}
