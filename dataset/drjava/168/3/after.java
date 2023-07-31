class PlaceHold {
  private void _openProjectUpdate() {
    if (_model.isProjectActive()) {
      _closeProjectAction.setEnabled(true);
      _saveProjectAction.setEnabled(true);
      _saveProjectAsAction.setEnabled(true);
      _projectPropertiesAction.setEnabled(true);
      _junitOpenProjectFilesAction.setEnabled(true);
      _compileProjectAction.setEnabled(true);
      _jarProjectAction.setEnabled(true);
      if (_model.getBuildDirectory() != null) {
        _cleanAction.setEnabled(true);
      }
      _resetNavigatorPane();
      _compileButton.setToolTipText(
          "<html>Compile all documents in the project.<br>External files are excluded.</html>");
    }
  }
}
