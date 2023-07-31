class PlaceHold {
  private void _openProjectUpdate() {
    if (_model.isProjectActive()) {
      _closeProjectAction.setEnabled(true);
      _saveProjectAction.setEnabled(true);
      _saveProjectAsAction.setEnabled(true);
      _exportProjectInOldFormatAction.setEnabled(true);
      _projectPropertiesAction.setEnabled(true);
      _junitProjectAction.setEnabled(true);
      _compileProjectAction.setEnabled(true);
      _jarProjectAction.setEnabled(true);
      if (_model.getBuildDirectory() != null) {
        _cleanAction.setEnabled(true);
      }
      _autoRefreshAction.setEnabled(true);
      _resetNavigatorPane();
    }
  }
}
