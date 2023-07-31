class PlaceHold {
  private void _openProjectUpdate() {
    if (_model.isProjectActive()) {
      _closeProjectAction.setEnabled(true);
      _projectPropertiesAction.setEnabled(true);
      _compileProjectAction.setEnabled(true);
      _model.setProjectChanged(false);
      _resetNavigatorPane();
    }
  }
}
