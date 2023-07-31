class PlaceHold {
  private void _openProjectUpdate() {
    if (_model.isProjectActive()) {
      _closeProjectAction.setEnabled(true);
      _saveProjectAction.setEnabled(true);
      _projectPropertiesAction.setEnabled(true);
      _junitProjectAction.setEnabled(true);
      _junitOpenProjectFilesAction.setEnabled(true);
      _compileOpenProjectAction.setEnabled(true);
      _compileProjectAction.setEnabled(true);
      if (_model.getBuildDirectory() != null) {
        _cleanAction.setEnabled(true);
      }
      _model.setProjectChanged(false);
      _resetNavigatorPane();
      _compileButton.setToolTipText(
          "<html>Compile all documents in the project.<br>External files are excluded.</html>");
    }
  }
}
