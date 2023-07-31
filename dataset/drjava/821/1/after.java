class PlaceHold {
  boolean _closeProject(boolean quitting) {
    if (_checkProjectClose()) {
      List<OpenDefinitionsDocument> projDocs = _model.getProjectDocuments();
      boolean couldClose = _model.closeFiles(projDocs);
      if (!couldClose) {
        return false;
      }
      if (quitting) {
        return true;
      }
      _model.closeProject(quitting);
      Component renderer = _model.getDocumentNavigator().getRenderer();
      new ForegroundColorListener(renderer);
      new BackgroundColorListener(renderer);
      _resetNavigatorPane();
      if (_model.getDocumentCount() == 1) {
        _model.setActiveFirstDocument();
      }
      _closeProjectAction.setEnabled(false);
      _saveProjectAction.setEnabled(false);
      _saveProjectAsAction.setEnabled(false);
      _projectPropertiesAction.setEnabled(false);
      _jarProjectAction.setEnabled(false);
      _junitProjectAction.setEnabled(false);
      _compileProjectAction.setEnabled(false);
      _setUpContextMenus();
      _currentProjFile = FileOps.NULL_FILE;
      return true;
    } else {
      return false;
    }
  }
}
