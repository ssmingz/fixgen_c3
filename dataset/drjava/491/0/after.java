class PlaceHold {
  boolean _closeProject() {
    if (_checkProjectClose()) {
      List<OpenDefinitionsDocument> projDocs = _model.getProjectDocuments();
      _model.closeFiles(projDocs);
      _model.closeProject();
      Component renderer = _model.getDocumentNavigator().getRenderer();
      new ForegroundColorListener(renderer);
      new BackgroundColorListener(renderer);
      _resetNavigatorPane();
      if (_model.getDocumentCount() == 1) {
        _model.setActiveFirstDocument();
      }
      _closeProjectAction.setEnabled(false);
      _saveProjectAction.setEnabled(false);
      _projectPropertiesAction.setEnabled(false);
      _junitProjectAction.setEnabled(false);
      _junitOpenProjectFilesAction.setEnabled(false);
      _compileOpenProjectAction.setEnabled(false);
      _compileProjectAction.setEnabled(false);
      _setUpContextMenus();
      _currentProjFile = null;
      _compileButton.setToolTipText("Compile all open documents");
      return true;
    } else {
      return false;
    }
  }
}
